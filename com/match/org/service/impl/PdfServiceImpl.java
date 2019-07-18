package com.match.org.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.match.org.cl.Zhengshucl;
import com.match.org.mapper.ZhengshuloadMapper;
import com.match.org.model.HuoJiangZhengShu;
import com.match.org.service.PdfService;
import com.match.proposition.mapper.TeamMapper;
import com.match.proposition.mapper.ThemeSignupMapper;
import javax.servlet.http.HttpServletResponse;

@Service
public class PdfServiceImpl implements PdfService {

	@Autowired
	private ThemeSignupMapper ThemeSignupMapper;

	@Autowired
	private TeamMapper TeamMapper;
	
	@Autowired
	private  ZhengshuloadMapper   ZhengshuloadMapper;

	@Override
	public String addPDFBaoMing(Map<String, Object> datemap, long id) throws Exception {

		// 利用模板生成pdf
		// 模板路径 
		String templatePath = "/root/wendang/A.pdf";
		// 生成的新文件路径
		// 读取pdf模板
//	        String newPDFPath = "E:/testout1.pdf";
		PdfReader reader = new PdfReader(templatePath);
//	        FileOutputStream out;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		PdfStamper stamper = new PdfStamper(reader, bos);
		try {
			// 给表单添加中文字体 这里采用系统字体。不设置的话，中文可能无法显示   c://windows//fonts//simsun.ttc,1
//			BaseFont bf = BaseFont.createFont("/usr/share/fonts/wqy-zenhei/wqy-zenhei.ttf", BaseFont.IDENTITY_H,
//					BaseFont.EMBEDDED);
			BaseFont bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);
//			Font FontChinese = new Font(bf, 5, Font.NORMAL);
			// 输出流
//	            out = new FileOutputStream(newPDFPath);
			PdfContentByte under = stamper.getUnderContent(1);
			AcroFields form = stamper.getAcroFields();
			// 遍历数据
			// 查询详情
			Map<String, Object> map = ThemeSignupMapper.findBaoMingPDFById(id);
			if (map.get("stwcode") != null) {
				datemap.put("twcode", map.get("stwcode").toString());
			}
			if (map.get("diname") != null) {
				datemap.put("diname", map.get("diname").toString());
			}
			if (map.get("collegename") != null && map.get("dename") != null) {
				String address = map.get("collegename").toString() + " " + map.get("dename").toString();
				datemap.put("collegename", address);
				datemap.put("address", address);
			}
			if (map.get("stitle") != null) {
				datemap.put("title", map.get("stitle").toString());
			}
			if (map.get("title") != null) {
				datemap.put("themenamethemename", map.get("title").toString());
			}
			if (map.get("teacher") != null) {
				datemap.put("t1", map.get("teacher").toString());
			}
			if (map.get("teacherphone") != null) {
				datemap.put("tp1", map.get("teacherphone").toString());
			}
			if (map.get("cardID") != null) {
				datemap.put("card", map.get("cardID").toString());
			}
			if (map.get("phone") != null) {
				datemap.put("phone", map.get("phone").toString());
			}
			if (map.get("email") != null) {
				datemap.put("email", map.get("email").toString());
			}
			if (map.get("qq") != null) {
				datemap.put("qq", map.get("qq").toString());
			}
			if (map.get("workType") != null && map.get("typename") != null) {
				datemap.put("worktype", map.get("workType").toString() + " " + map.get("typename").toString());
			}

			// 查询team
			List<Map<String, Object>> list = TeamMapper.findTeamInfo(id);
			if(list!=null && list.size()>0)
			{
				for (int i = 0; i < list.size(); i++) {
					Map<String, Object> tmap = list.get(i);
					if (tmap != null) {
						datemap.put("s" + (i + 1), tmap.get("name").toString());
						datemap.put("c" + (i + 1), tmap.get("divisionwork").toString());
						datemap.put("g" + (i + 1), tmap.get("grade").toString());
						datemap.put("d" + (i + 1), tmap.get("major").toString());
					}
				}
			}

			// 添加所创建的字体
			form.addSubstitutionFont(bf);
			for (String key : datemap.keySet()) {
				String value = datemap.get(key).toString();
				form.setField(key, value);
			}
//	            Map<String,Object> qrcodeFields=(Map<String, Object>) map.get("qrcodeFields");
//	            //遍历二维码字段
//	            for (Map.Entry<String, Object> entry : qrcodeFields.entrySet()) {
//	                String key = entry.getKey();
//	                Object value = entry.getValue();
//	                // 获取属性的类型
//	                if(value != null && form.getField(key) != null){
//	                    //获取位置(左上右下)
//	                    FieldPosition fieldPosition = form.getFieldPositions(key).get(0);
//	                    //绘制二维码
//	                    float width = fieldPosition.position.getRight() - fieldPosition.position.getLeft();
//	                    BarcodeQRCode pdf417 = new BarcodeQRCode(value.toString(), (int)width, (int)width, null);
//	                    //生成二维码图像
//	                    Image image128 = pdf417.getImage();
//	                    //绘制在第一页
//	                    PdfContentByte cb = stamper.getOverContent(1);
//	                    //左边距(居中处理)
//	                    float marginLeft = (fieldPosition.position.getRight() - fieldPosition.position.getLeft() - image128.getWidth()) / 2;
//	                    //条码位置
//	                    image128.setAbsolutePosition(fieldPosition.position.getLeft() + marginLeft, fieldPosition.position.getBottom());
//	                    //加入条码
//	                    cb.addImage(image128);
//	                }
//	            }
//
//	            //遍历条码字段
//	            Map<String,Object> barcodeFields=(Map<String, Object>) map.get("barcodeFields");
//	            for (Map.Entry<String, Object> entry : barcodeFields.entrySet()) {
//	                String key = entry.getKey();
//	                Object value = entry.getValue();
//	                // 获取属性的类型
//	                if(value != null && form.getField(key) != null){
//	                    //获取位置(左上右下)
//	                    FieldPosition fieldPosition = form.getFieldPositions(key).get(0);
//	                    //绘制条码
//	                    Barcode128 barcode128 = new Barcode128();
//	                    //字号
//	                    barcode128.setSize(10);
//	                    //条码高度
//	                    barcode128.setBarHeight(35);
//	                    //条码与数字间距
//	                    barcode128.setBaseline(10);
//	                    //条码值
//	                    barcode128.setCode(value.toString());
//	                    barcode128.setStartStopText(false);
//	                    barcode128.setExtended(true);
//	                    //绘制在第一页
//	                    PdfContentByte cb = stamper.getOverContent(1);
//	                    //生成条码图片
//	                    Image image128 = barcode128.createImageWithBarcode(cb, null, null);
//	                    //左边距(居中处理)
//	                    float marginLeft = (fieldPosition.position.getRight() - fieldPosition.position.getLeft() - image128.getWidth()) / 2;
//	                    //条码位置
//	                    image128.setAbsolutePosition(fieldPosition.position.getLeft() + marginLeft, fieldPosition.position.getBottom());
//	                    //加入条码
//	                    cb.addImage(image128);
//	                }
//	            }
//	             //图片类的内容处理
//	            Map<String,String> imgmap = (Map<String,String>)map.get("imgmap");
//	            for(String key : imgmap.keySet()) {
//	                String value = imgmap.get(key);
//	                String imgpath = value;
//	                int pageNo = form.getFieldPositions(key).get(0).page;
//	                Rectangle signRect = form.getFieldPositions(key).get(0).position;
//	                float x = signRect.getLeft();
//	                float y = signRect.getBottom();
//	                //根据路径读取图片
//	                Image image = Image.getInstance(imgpath);
//	                //获取图片页面
//	                PdfContentByte under = stamper.getOverContent(pageNo);
//	                //图片大小自适应
//	                image.scaleToFit(signRect.getWidth(), signRect.getHeight());
//	                //添加图片
//	                image.setAbsolutePosition(x, y);
//	                under.addImage(image);
//	            }
			// 如果为false，生成的PDF文件可以编辑，如果为true，生成的PDF文件不可以编辑
			stamper.setFormFlattening(true);
			stamper.close();
//	            Document doc = new Document();
//	            Font font = new Font(bf, 20);
//	            PdfCopy copy = new PdfCopy(doc, out);
//	            doc.open();
//	            PdfImportedPage importPage = copy.getImportedPage(new PdfReader(bos.toByteArray()), 1);
//	            copy.addPage(importPage);
//	            doc.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		String result = new String(bos.toByteArray(), "ISO-8859-1");// 转字符串设置编码
		return result;
	}

	@Override
	public String PdfZhengshuFile(HuoJiangZhengShu huoJiangZhengShu) throws Exception {
		
		String pname = huoJiangZhengShu.getPrizename();
		int type = 0 ;
		if(pname.equals("一等奖"))
		{
			type=1;
		}
		else if(pname.equals("二等奖"))
		{
			type=2;
		}
		else if(pname.equals("三等奖"))
		{
			type=3;
		}
		else if(pname.equals("优秀奖"))
		{
			type=4;
		}
		String templatePath  ="";
		Map<String,Object> map = ZhengshuloadMapper.findPath(type, huoJiangZhengShu.getCompetitionid());
		if(map!=null)
		{
			templatePath = Zhengshucl.path+map.get("path").toString();
		}
		// 读取pdf模板
		PdfReader reader = new PdfReader(templatePath);
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		PdfStamper stamper = new PdfStamper(reader, bos);
		Map<String, Object> datemap = new HashMap<String, Object>();
		try {
			// 给表单添加中文字体 这里采用系统字体。不设置的话，中文可能无法显示   c://windows//fonts//simsun.ttc,1
			BaseFont bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);
//			Font chineseFont= new Font(bf, 12, Font.NORMAL);
//			PdfContentByte under = stamper.getUnderContent(1);
			AcroFields form = stamper.getAcroFields();

			if (huoJiangZhengShu.getStwcode()!= null) {
				datemap.put(Zhengshucl.stwcode, huoJiangZhengShu.getStwcode());
			}
			if (huoJiangZhengShu.getTitle()!= null) {
				datemap.put(Zhengshucl.title, huoJiangZhengShu.getTitle());
			}
			if (huoJiangZhengShu.getRealName()!=null) {
				datemap.put(Zhengshucl.realName, huoJiangZhengShu.getRealName());
			}
			if (huoJiangZhengShu.getTeacherename()!=null) {
				datemap.put(Zhengshucl.teacherename, huoJiangZhengShu.getTeacherename());
			}
			if (huoJiangZhengShu.getCollegename()!=null) {
				datemap.put(Zhengshucl.collegename, huoJiangZhengShu.getCollegename());
			}

			// 添加所创建的字体
			form.addSubstitutionFont(bf);
			for (String key : datemap.keySet()) {
				String value = datemap.get(key).toString();
				form.setField(key, value);
			}
			
			// 如果为false，生成的PDF文件可以编辑，如果为true，生成的PDF文件不可以编辑
			stamper.setFormFlattening(true);
			stamper.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		String result = new String(bos.toByteArray(), "ISO-8859-1");// 转字符串设置编码
		return result;
	}

}
