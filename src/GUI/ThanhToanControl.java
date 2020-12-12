package GUI;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import javax.print.Doc;
import javax.print.DocPrintJob;
import javax.print.SimpleDoc;
import javax.print.StreamPrintService;
import javax.print.StreamPrintServiceFactory;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;

import DAO.DAOHoaDon;
import common.Common;
import entity.HoaDon;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ThanhToanControl implements Initializable {
	private boolean trangThaiThanhToan = false;

	public boolean isTrangThaiThanhToan() {
		return trangThaiThanhToan;
	}

	public void setTrangThaiThanhToan(boolean trangThaiThanhToan) {
		this.trangThaiThanhToan = trangThaiThanhToan;
	}

	private Common common = new Common();
	private HoaDon hoaDon;

	private DAOHoaDon daoHoaDon = new DAOHoaDon();

	public Text lblKhachHang;
	public Text lblTenKhachHang;
	public Text lblDiemTichLuy;
	public Text lblDiemTichLuyKhachHang;
	public Text lblSuDungDiemTichLuy;
	public TextField txtSuDungDiemTichLuy;
	public Text lblDiemTichLuyDuoc;
	public Text lblSoDiemTichLuyDuoc;

	public Text lblTongTien;
	public Text lblThue;
	public Text lblThanhTien;
	public Text lblTienThua;
	public TextField txtTienKhachDua;
	public Text lblTienPhaiTra;

	public HoaDon getHoaDon() {
		return hoaDon;
	}

	public void setHoaDon(HoaDon hoaDon) {
		this.hoaDon = hoaDon;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		khoiTaoThanhToan();
		handleThanhTienWhenSuDungDiemTichLuy();
		handleTienKhachDuaField();
	}

	private void handleTienKhachDuaField() {
		// TODO Auto-generated method stub
		txtTienKhachDua.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				// TODO Auto-generated method stub
				if (!newValue.matches("^[0-9]*")) {
					txtTienKhachDua.setText(oldValue);
				}

				handleTienPhaiTra();
			}
		});
	}

	private void handleThanhTienWhenSuDungDiemTichLuy() {
		// TODO Auto-generated method stub
		txtSuDungDiemTichLuy.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				// TODO Auto-generated method stub
				if (!newValue.matches("^[0-9]*")) {
					txtSuDungDiemTichLuy.setText(oldValue);
				}

				if (txtSuDungDiemTichLuy.getText().isEmpty()) {
					hoaDon.setDiemSuDung(0);
					lblTienPhaiTra.setText(common.formatMoney(hoaDon.getTienPhaiTra()));
					handleTienPhaiTra();
				}else {
					if (Double.parseDouble(txtSuDungDiemTichLuy.getText()) > hoaDon.getKhachHang().getDienTichLuy()) {
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Information Dialog");
						alert.setHeaderText(null);
						alert.setContentText("Diểm tích lũy không đủ!");

						alert.showAndWait();

						txtSuDungDiemTichLuy.setText(oldValue);
						hoaDon.setDiemSuDung(Double.parseDouble(txtSuDungDiemTichLuy.getText()));
					}else {
						hoaDon.setDiemSuDung(Double.parseDouble(txtSuDungDiemTichLuy.getText()));

						if (hoaDon.getTienPhaiTra() < 0) {
							Alert alert = new Alert(AlertType.INFORMATION);
							alert.setTitle("Information Dialog");
							alert.setHeaderText(null);
							alert.setContentText("Tiền phải trả phải lớn hơn hoặc bằng 0!");

							alert.showAndWait();
							txtSuDungDiemTichLuy.setText(oldValue);
							hoaDon.setDiemSuDung(Double.parseDouble(txtSuDungDiemTichLuy.getText()));
						}else {
							lblTienPhaiTra.setText(common.formatMoney(hoaDon.getTienPhaiTra()));
							handleTienPhaiTra();
						}
					}
				}
			}
		});
	}

	private void handleTienPhaiTra() {
		// TODO Auto-generated method stub
		if (txtTienKhachDua.getText().isEmpty()) {
			lblTienThua.setText(common.formatMoney(0 - hoaDon.getTienPhaiTra()));
		}else {
			lblTienThua.setText(common.formatMoney(Double.parseDouble(txtTienKhachDua.getText())
					- hoaDon.getTienPhaiTra() 
					));
		}
	}

	private void khoiTaoThanhToan() {
		// TODO Auto-generated method stub
		if (this.hoaDon.getKhachHang() == null) {
			lblKhachHang.setVisible(false);
			lblTenKhachHang.setVisible(false);
			lblDiemTichLuy.setVisible(false);
			lblDiemTichLuyKhachHang.setVisible(false);
			lblSuDungDiemTichLuy.setVisible(false);
			txtSuDungDiemTichLuy.setVisible(false);
			lblDiemTichLuyDuoc.setVisible(false);
			lblSoDiemTichLuyDuoc.setVisible(false);

			lblTongTien.setText(common.formatMoney(hoaDon.tinhTienHoaDonChuaThue()));
			lblThue.setText(common.formatMoney(hoaDon.tinhThueHoaDon()));
			lblThanhTien.setText(common.formatMoney(hoaDon.tinhTienHoaDonBaoGomThue()));
			lblTienPhaiTra.setText(common.formatMoney(hoaDon.getTienPhaiTra()));
			lblTienThua.setText(common.formatMoney(0 - hoaDon.getTienPhaiTra()));
		}else {
			lblTongTien.setText(common.formatMoney(hoaDon.tinhTienHoaDonChuaThue()));
			lblThue.setText(common.formatMoney(hoaDon.tinhThueHoaDon()));
			lblThanhTien.setText(common.formatMoney(hoaDon.tinhTienHoaDonBaoGomThue()));
			lblTenKhachHang.setText(hoaDon.getKhachHang().getHoTenDem()+" "+hoaDon.getKhachHang().getTen());
			lblDiemTichLuyKhachHang.setText(common.formatMoney(hoaDon.getKhachHang().getDienTichLuy()));
			lblSoDiemTichLuyDuoc.setText(common.formatMoney(hoaDon.tinhDiemTichLuy()));
			lblTienPhaiTra.setText(common.formatMoney(hoaDon.getTienPhaiTra()));
			lblTienThua.setText(common.formatMoney(0 - hoaDon.getTienPhaiTra()));
		}
	}

	@FXML
	public void btnThoat(ActionEvent event) {
		Stage thanhToanStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		thanhToanStage.close();
	}

	@FXML
	public void btnThanhToan(ActionEvent event) {
		double tienKhachDua;
		if (txtTienKhachDua.getText().isEmpty()) {
			tienKhachDua = 0;
		}else {
			tienKhachDua = Double.parseDouble(txtTienKhachDua.getText());
		}

		if (tienKhachDua < hoaDon.getTienPhaiTra()) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Khách chưa đưa đủ tiền!");

			alert.showAndWait();
		}else {
			System.out.println(hoaDon);
			if (daoHoaDon.TaoHoaDon(hoaDon)) {
				this.trangThaiThanhToan = true;
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information Dialog");
				alert.setHeaderText(null);
				alert.setContentText("Thanh toán thành công!");

				alert.showAndWait();
				Stage thanhToanStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
				thanhToanStage.close();



				PrinterJob pj = PrinterJob.getPrinterJob();        
				pj.setPrintable(new BillPrintable(),getPageFormat(pj));
				try {
					pj.print();
				} catch (PrinterException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information Dialog");
				alert.setHeaderText(null);
				alert.setContentText("Thanh toán không thành công!");

				alert.showAndWait();
			}

		}
	}



	public static PageFormat getPageFormat(PrinterJob pj)
	{

		PageFormat pf = pj.defaultPage();
		Paper paper = pf.getPaper();    

		double middleHeight =8.0;  
		double headerHeight = 2.0;                  
		double footerHeight = 2.0;                  
		double width = convert_CM_To_PPI(8);      //printer know only point per inch.default value is 72ppi
		double height = convert_CM_To_PPI(headerHeight+middleHeight+footerHeight); 
		paper.setSize(width, height);
		paper.setImageableArea(                    
				0,
				10,
				width,            
				height - convert_CM_To_PPI(1)
				);   //define boarder size    after that print area width is about 180 points

		pf.setOrientation(PageFormat.PORTRAIT);           //select orientation portrait or landscape but for this time portrait
		pf.setPaper(paper);    

		return pf;
	}

	protected static double convert_CM_To_PPI(double cm) {            
		return toPPI(cm * 0.393600787);            
	}

	protected static double toPPI(double inch) {            
		return inch * 72d;            
	}

	public class BillPrintable implements Printable {

		private int y=20;


		public int print(Graphics graphics, PageFormat pageFormat,int pageIndex) 
				throws PrinterException 
		{    



			int result = NO_SUCH_PAGE;    
			if (pageIndex == 0) {                    

				Graphics2D g2d = (Graphics2D) graphics;                    

				double width = pageFormat.getImageableWidth();                    

				g2d.translate((int) pageFormat.getImageableX(),(int) pageFormat.getImageableY()); 

				////////// code by alqama//////////////

				FontMetrics metrics=g2d.getFontMetrics(new Font("Arial",Font.BOLD,7));
				//    int idLength=metrics.stringWidth("000000");
				//int idLength=metrics.stringWidth("00");
				int idLength=metrics.stringWidth("000");
				int amtLength=metrics.stringWidth("000000");
				int qtyLength=metrics.stringWidth("00000");
				int priceLength=metrics.stringWidth("000000");
				int prodLength=(int)width - idLength - amtLength - qtyLength - priceLength-17;

				//    int idPosition=0;
				//    int productPosition=idPosition + idLength + 2;
				//    int pricePosition=productPosition + prodLength +10;
				//    int qtyPosition=pricePosition + priceLength + 2;
				//    int amtPosition=qtyPosition + qtyLength + 2;

				int productPosition = 0;
				int discountPosition= prodLength+5;
				int pricePosition = discountPosition +idLength+10;
				int qtyPosition=pricePosition + priceLength + 4;
				int amtPosition=qtyPosition + qtyLength;



				try{
					/*Draw Header*/
					int yShift = 10;
					int headerRectHeight=15;
					int headerRectHeighta=40;

					///////////////// Product names Get ///////////

					///////////////// Product names Get ///////////


					///////////////// Product price Get ///////////
					///////////////// Product price Get ///////////

					g2d.setFont(new Font("Monospaced",Font.PLAIN,9));
					g2d.drawString("-------------------------------------",12,y);y+=yShift;
					g2d.drawString("      Restaurant Bill Receipt        ",12,y);y+=yShift;
					g2d.drawString("-------------------------------------",12,y);y+=headerRectHeight;

					g2d.drawString("-------------------------------------",10,y);y+=yShift;
					g2d.drawString(" Food Name                 T.Price   ",10,y);y+=yShift;
					g2d.drawString("-------------------------------------",10,y);y+=headerRectHeight;

					hoaDon.getDsChiTietHoaDon().forEach(i -> {
						g2d.drawString(" "+i.getThuoc().getTenThuoc()+"                  "+i.tinhTongTienChuaThue()+"  ",10,y);y+=yShift;
					});


					g2d.drawString("-------------------------------------",10,y);y+=yShift;
					g2d.drawString(" Total amount: "+hoaDon.tinhTienHoaDonBaoGomThue()+"               ",10,y);y+=yShift;
					g2d.drawString("-------------------------------------",10,y);y+=yShift;
					g2d.drawString("          Free Home Delivery         ",10,y);y+=yShift;
					g2d.drawString("             03111111111             ",10,y);y+=yShift;
					g2d.drawString("*************************************",10,y);y+=yShift;
					g2d.drawString("    THANKS TO VISIT OUR RESTUARANT   ",10,y);y+=yShift;
					g2d.drawString("*************************************",10,y);y+=yShift;





					//		            g2d.setFont(new Font("Monospaced",Font.BOLD,10));
					//		            g2d.drawString("Customer Shopping Invoice", 30,y);y+=yShift; 


				}
				catch(Exception r){
					r.printStackTrace();
				}

				result = PAGE_EXISTS;    
			}    
			return result;    
		}
	}

}
