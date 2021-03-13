package com.project.musicshopweb.controllers;

import java.io.IOException;
import java.text.DateFormatSymbols;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.axes.cartesian.CartesianScales;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearTicks;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.bar.BarChartOptions;
import org.primefaces.model.charts.optionconfig.title.Title;

import com.project.musicshopentities.dto.AlbumTopDTO;
import com.project.musicshopentities.dto.ArtistAlbumDTO;
import com.project.musicshopentities.entities.CartAlbum;
import com.project.musicshopservices.service.AlbumService;
import com.project.musicshopservices.service.CartService;
import com.project.musicshopservices.service.HomeService;
import com.project.musicshopweb.enums.ColorEnum;
import com.project.musicshopweb.session.SessionBean;
import com.project.musicshopweb.utils.CommonsUtils;

import lombok.Data;

@ManagedBean
@ViewScoped
@Data
public class HomeController {
	private static final Logger LOGGER = LogManager.getLogger(HomeController.class);
	private String filter;
	private List<ArtistAlbumDTO> artistAlbumDTO;
	@ManagedProperty("#{homeServiceImpl}")
	private HomeService homeServiceImpl;
	@ManagedProperty("#{sessionBean}")
	private SessionBean sessionBean;
	@ManagedProperty("#{cartServiceImpl}")
	private CartService cartServiceImpl;
	private BarChartModel barChartModel;
	@ManagedProperty("#{albumServiceImpl}")
	private AlbumService albumServiceImpl;
	@PostConstruct
	public void init() {
		LOGGER.info("INFO");
		if (this.sessionBean.getPerson().getRol().getIdRol() == 3) {
			this.createGraphicAlbums();
		}
	}
	public void consultAlbumsArtistsByFilter() {
		try {
			this.artistAlbumDTO = this.homeServiceImpl.consultAlbumByFilter(this.filter);
			if(this.artistAlbumDTO != null) {
				for (ArtistAlbumDTO artistAlbums : artistAlbumDTO) {
					LOGGER.info("Artista: " + artistAlbums.getArtist().getName());
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getCause());
		}
	}
	public void showDetail(ArtistAlbumDTO artistAlbum) {
		try {
			this.sessionBean.setArtistAlbumDTO(artistAlbum);
			CommonsUtils.redirect("/pages/client/detail.xhtml");
			LOGGER.info("Entrando...");
		} catch (IOException e) {
			CommonsUtils.showMessage(FacesMessage.SEVERITY_FATAL, "Fatal", "Hubo un error de formato");
			LOGGER.info(e.getCause());
		}
	}
	public void addAlbumCart(ArtistAlbumDTO artistAlbumDTO) {
		CartAlbum cartAlbum = this.cartServiceImpl.saveAlbumsCart(artistAlbumDTO, this.sessionBean.getPerson().getCart(), 1);
		List<CartAlbum> cartAlbums = this.sessionBean.getPerson().getCart().getCartAlbums();
		cartAlbums.add(cartAlbum);
	}
	public void createGraphicAlbums() {
		this.barChartModel = new BarChartModel();
		ChartData chartData = new ChartData();
		List<AlbumTopDTO> albumsTop = this.albumServiceImpl.consultAlbumsTop();
		String[] months = new DateFormatSymbols().getMonths();
		for (int i = 0; i < albumsTop.size(); i++) {
			BarChartDataSet barChartDataSet = new BarChartDataSet();
			barChartDataSet.setLabel(albumsTop.get(i).getCartAlbum().getAlbum().getName());
			barChartDataSet.setBackgroundColor(ColorEnum.values()[i].getString());
			barChartDataSet.setBorderWidth(1);
			List<Number> numbers = new ArrayList<Number>();
			String monthPurchased = albumsTop.get(i).getCartAlbum().getDatePurchase().getMonth().getDisplayName(TextStyle.FULL, new Locale("es", "ES"));
			for (int j = 0; j < months.length; j++) {
				String month = months[j];
				if (month.equals(monthPurchased)) {
					numbers.add(albumsTop.get(i).getAmountTotal());
				} else {
					numbers.add(0);
				}
			}
			barChartDataSet.setData(numbers);
			chartData.addChartDataSet(barChartDataSet);
		}
		List<String> monthsString = new ArrayList<String>();
		monthsString.add("Enero");
		monthsString.add("Febrero");
		monthsString.add("Marzo");
		monthsString.add("Abril");
		monthsString.add("Mayo");
		monthsString.add("Junio");
		monthsString.add("Julio");
		monthsString.add("Agosto");
		monthsString.add("Septiembre");
		monthsString.add("Octubre");
		monthsString.add("Noviembre");
		monthsString.add("Diciembre");
		chartData.setLabels(monthsString);
		this.barChartModel.setData(chartData);
		BarChartOptions barChartOptions = new BarChartOptions();
		CartesianScales cartesianScales = new CartesianScales();
		CartesianLinearAxes cartesianLinearAxes = new CartesianLinearAxes();
		CartesianLinearTicks cartesianLinearTicks = new CartesianLinearTicks();
		cartesianLinearTicks.setBeginAtZero(true);
		cartesianLinearAxes.setTicks(cartesianLinearTicks);
		cartesianScales.addYAxesData(cartesianLinearAxes);
		Title title = new Title();
		title.setDisplay(true);
		title.setText("Top 10 Albums más vendidos por mes");
		barChartOptions.setTitle(title);
		this.barChartModel.setOptions(barChartOptions);
	}
}
