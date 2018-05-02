///**
// * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
// */
//package com.xgx.legion.utils.excel;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.lang.reflect.Field;
//import java.lang.reflect.Method;
//import java.math.BigDecimal;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.Comparator;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//import java.util.ResourceBundle;
//import java.util.UUID;
//import java.util.regex.Pattern;
//
//import org.apache.commons.lang3.StringUtils;
//import org.apache.poi.hssf.usermodel.HSSFCell;
//import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
//import org.apache.poi.hssf.usermodel.HSSFDataFormat;
//import org.apache.poi.hssf.usermodel.HSSFDateUtil;
//import org.apache.poi.hssf.usermodel.HSSFPatriarch;
//import org.apache.poi.hssf.usermodel.HSSFPicture;
//import org.apache.poi.hssf.usermodel.HSSFPictureData;
//import org.apache.poi.hssf.usermodel.HSSFRow;
//import org.apache.poi.hssf.usermodel.HSSFShape;
//import org.apache.poi.hssf.usermodel.HSSFSheet;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.DateUtil;
//import org.apache.tools.ant.util.FileUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.util.CollectionUtils;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.dinpay.dpp.mms.manage.annotation.ExcelField;
//
//import net.coobird.thumbnailator.Thumbnails;
//
//
///**
// * 导入Excel文件（支持“XLS”格式）
// * @author ThinkGem
// * @version 2013-03-10
// */
//public class ImportExcel {
//	
//	private static Logger log = LoggerFactory.getLogger(ImportExcel.class);
//	
//	private static final String localTempPath = ResourceBundle.getBundle("config").getString("downloadFilePath");
//	
//	/**
//	 * 工作薄对象
//	 */
//	private HSSFWorkbook wb;
//	
//	/**
//	 * 工作表对象
//	 */
//	private HSSFSheet sheet;
//	
//	/**
//	 * 标题行号
//	 */
//	private int headerNum;
//	
//	/**
//	 * 是否是英文环境  true: 是     false：否
//	 */
//	private boolean engFlag;
//	
//	/**
//	 * 构造函数
//	 * @param path 导入文件，读取第一个工作表
//	 * @param headerNum 标题行号，数据行号=标题行号+1
//	 * @throws InvalidFormatException 
//	 * @throws IOException 
//	 */
//	public ImportExcel(String fileName, int headerNum) 
//			throws InvalidFormatException, IOException {
//		this(new File(fileName), headerNum);
//	}
//	
//	/**
//	 * 构造函数
//	 * @param path 导入文件对象，读取第一个工作表
//	 * @param headerNum 标题行号，数据行号=标题行号+1
//	 * @throws InvalidFormatException 
//	 * @throws IOException 
//	 */
//	public ImportExcel(File file, int headerNum) 
//			throws InvalidFormatException, IOException {
//		this(file, headerNum, 0);
//	}
//
//	/**
//	 * 构造函数
//	 * @param path 导入文件
//	 * @param headerNum 标题行号，数据行号=标题行号+1
//	 * @param sheetIndex 工作表编号
//	 * @throws InvalidFormatException 
//	 * @throws IOException 
//	 */
//	public ImportExcel(String fileName, int headerNum, int sheetIndex) 
//			throws InvalidFormatException, IOException {
//		this(new File(fileName), headerNum, sheetIndex);
//	}
//	
//	/**
//	 * 构造函数
//	 * @param path 导入文件对象
//	 * @param headerNum 标题行号，数据行号=标题行号+1
//	 * @param sheetIndex 工作表编号
//	 * @throws InvalidFormatException 
//	 * @throws IOException 
//	 */
//	public ImportExcel(File file, int headerNum, int sheetIndex) 
//			throws InvalidFormatException, IOException {
//		this(file.getName(), new FileInputStream(file), headerNum, sheetIndex);
//	}
//	
//	/**
//	 * 构造函数
//	 * @param file 导入文件对象
//	 * @param headerNum 标题行号，数据行号=标题行号+1
//	 * @param sheetIndex 工作表编号
//	 * @throws InvalidFormatException 
//	 * @throws IOException 
//	 */
//	public ImportExcel(MultipartFile multipartFile, int headerNum, int sheetIndex) 
//			throws InvalidFormatException, IOException {
//		this(multipartFile.getOriginalFilename(), multipartFile.getInputStream(), headerNum, sheetIndex);
//	}
//
//	/**
//	 * 构造函数
//	 * @param path 导入文件对象
//	 * @param headerNum 标题行号，数据行号=标题行号+1
//	 * @param sheetIndex 工作表编号
//	 * @throws InvalidFormatException 
//	 * @throws IOException 
//	 */
//	public ImportExcel(String fileName, InputStream is, int headerNum, int sheetIndex) 
//			throws InvalidFormatException, IOException {
//		if (StringUtils.isBlank(fileName)){
//			throw new RuntimeException("导入文档为空!");
//		}else{  
//        	this.wb = new HSSFWorkbook(is);
//        }  
//		if (this.wb.getNumberOfSheets()<sheetIndex){
//			throw new RuntimeException("文档中没有工作表!");
//		}
//		this.sheet = this.wb.getSheetAt(sheetIndex);
//		this.headerNum = headerNum;
//		log.debug("Initialize success.");
//	}
//	
//	/**
//	 * 获取行对象
//	 * @param rownum
//	 * @return
//	 */
//	public HSSFRow getRow(int rownum){
//		return this.sheet.getRow(rownum);
//	}
//
//	/**
//	 * 获取数据行的开始行号（通常是excel文件中的第2行，在poi中行号是以0开头，所有数据行号的开头一般是1）
//	 * @return
//	 */
//	public int getDataRowNum(){
//		return headerNum + 1;
//	}
//	
//	/**
//	 * 获取最后一个数据行号
//	 * @return
//	 */
//	public int getLastDataRowNum(){
//		return this.sheet.getLastRowNum()+headerNum;
//	}
//	
//	/**
//	 * 获取最后一个列号
//	 * @return
//	 */
//	public int getLastCellNum(){
//		return this.getRow(headerNum).getLastCellNum();
//	}
//	
//	/**
//	 * 获取单元格值
//	 * @param row 获取的行
//	 * @param column 获取单元格列号
//	 * @return 单元格值
//	 */
//	public Object getCellValue(HSSFRow row, int column, Object os){
//		Object val = "";
//		try{
//			HSSFCell cell = row.getCell((short)column);
//			if (cell != null){
//				Class valType = ImportExcel.getFieldClassType(os);
//				if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
//					/*旧版的判断
//					 * if (valType == String.class) {
//						val = String.valueOf((long)cell.getNumericCellValue());
//					} else {
//						val = cell.getNumericCellValue();
//					}*/
//					if (HSSFDateUtil.isCellDateFormatted(cell)) {// 处理日期格式、时间格式  
//		                SimpleDateFormat sdf = null;  
//		                if (cell.getCellStyle().getDataFormat() == HSSFDataFormat.getBuiltinFormat("h:mm")) {  
//		                    sdf = new SimpleDateFormat("HH:mm");  
//		                } else {// 日期  
//		                    sdf = new SimpleDateFormat("yyyy/MM/dd");  
//		                }  
//		                Date date = cell.getDateCellValue();  
//		                val = sdf.format(date);  
//		            } else if (valType == String.class) {
//						val = String.valueOf((long)cell.getNumericCellValue());
//					} else {
//						val = cell.getNumericCellValue();
//					} 
//				}else if (cell.getCellType() == Cell.CELL_TYPE_STRING){
//					val = cell.getRichStringCellValue().toString();
//				}else if (cell.getCellType() == Cell.CELL_TYPE_FORMULA){
//					val = cell.getCellFormula();
//				}else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN){
//					val = cell.getBooleanCellValue();
//				}else if (cell.getCellType() == Cell.CELL_TYPE_ERROR){
//					val = cell.getErrorCellValue();
//				}
//			}
//		}catch (Exception e) {
//			return val;
//		}
//		return val;
//	}
//	
//	public Map<String, List<HSSFPictureData>> findAllPictureData(HSSFSheet sheet){
//
//	       Map<String, List<HSSFPictureData>> dataMap = null;
//
//	       //处理sheet中的图形
//	       HSSFPatriarch hssfPatriarch = sheet.getDrawingPatriarch();
//	       
//	       if(hssfPatriarch != null){
//	    	 //获取所有的形状图
//		       List<HSSFShape> shapes = hssfPatriarch.getChildren();
//
//		       if(shapes.size()>0){
//
//		           dataMap = new HashMap<String, List<HSSFPictureData>>();
//
//		           List<HSSFPictureData> pictureDataList = null;
//		           
//		           for (Iterator<HSSFShape> it = shapes.iterator(); it.hasNext();) {
//		        	   HSSFShape sp = it.next();
//		        	   if(sp instanceof HSSFPicture){
//		                   //转换
//		                   HSSFPicture picture = (HSSFPicture)sp;
//		                   //获取图片数据
//		                   HSSFPictureData pictureData = picture.getPictureData();
//		                   //图形定位
//		                   if(picture.getAnchor() instanceof HSSFClientAnchor){
//
//		                       HSSFClientAnchor anchor = (HSSFClientAnchor)picture.getAnchor();
//		                       //获取图片所在行作为key值,插入图片时，默认图片只占一行的单个格子，不能超出格子边界
//		                       
//		                       // 测试部分
//		                       int row1 = anchor.getRow1();
//		                       int row2 = anchor.getRow2();
//		                       short col1 = anchor.getCol1();
//		                       short col2 = anchor.getCol2();
//		                       if(row1 != row2){
//		                    	   continue;
//		                       }
//		                       
//		                       String rowNum = String.valueOf(row1);
//		                       String colNum = String.valueOf(col1);
//		                       if(dataMap.get(rowNum+"|"+colNum)!=null){
//		                               pictureDataList = dataMap.get(rowNum+"|"+colNum);
//		                       }else{
//		                               pictureDataList = new ArrayList<HSSFPictureData>();
//		                       }
//		                       pictureDataList.add(pictureData);
//		                       
//		                       // 若图片在同一单元格，则添加进图片map
//		                       if(row1==row2 && col1==col2){
//		                    	   dataMap.put(rowNum+"|"+col1, pictureDataList);
//		                    	   log.info("row1: "+row1+" , row2: "+row2+" , col1: "+col1+" , col2: "+col2);
//		                       }
//		                       
//		                       //int dx1 = anchor.getDx1();
//		                       //int dx2 = anchor.getDx2();
//		                       //int dy1 = anchor.getDy1();
//		                       //int dy2 = anchor.getDy2();
//		                       //System.out.println("dx1: "+dx1+" , dx2: "+dx2+" , dy1: "+dy1+" , dy2: "+dy2);
//		                   }
//		               }
//		           }
//		       } 
//	       }
//
//	       System.out.println("********图片数量明细 START********");
//	       int t=0;
//	       if(dataMap!=null){
//	           t=dataMap.keySet().size();
//	       }
//	       if(t>0){
//	               for(String key : dataMap.keySet()){
//	                   System.out.println("第 "+key.split("\\|")[0]+"行, 第 "+key.split("\\|")[1]+"列, 有图片： "+ dataMap.get(key).size() +" 张");
//	               }
//	       }else{
//	               System.out.println("Excel表中没有图片!");
//	       }
//	       System.out.println("********图片数量明细 END ********");
//
//	       return dataMap;
//	   }
//	
//	
//	private Map<String, List<String>> uploadPicture(Map<String, List<HSSFPictureData>> pictureMap, long pictrueMaxPixel, String imgUploadPath) {
//		
//		Map<String, List<String>> uploadPathMap = new HashMap<String, List<String>>();
//		
//		//TODO
//		String uploadUrl= imgUploadPath; 
//		String tempUploadUrl=localTempPath; 
//		
//		// 路径(服务器路径)
//		Date date = new Date();
//		SimpleDateFormat dateSf = new SimpleDateFormat("yyyyMMdd");
//		String dateStr = dateSf.format(date).toString();
//		String path = uploadUrl;
//		String tempPath = tempUploadUrl;
//
//		// 创建上传文件夹
//		File folder = new File(path + File.separator + dateStr);
//		if (!folder.exists() && !folder.isDirectory()) {
//			folder.mkdirs();
//		}
//		
//		// 创建上传临时文件夹
//		File tempPathFolder = new File(tempPath + File.separator + dateStr);
//		if (!tempPathFolder.exists() && !tempPathFolder.isDirectory()) {
//			tempPathFolder.mkdirs();
//		}
//		
//		// 上传图片 并 返回每一行图片路径
//		for (Map.Entry<String, List<HSSFPictureData>> entry : pictureMap.entrySet()) {
//			List<String> tempUploadPath = new ArrayList<String>();
//			List<HSSFPictureData> alist = entry.getValue();
//			for (HSSFPictureData pictureData : alist) {
//				// 获取图片格式
//				String ext = pictureData.suggestFileExtension();
//				// 获取图片索引
//				String picName = dateStr + "/" +UUID.randomUUID().toString().replaceAll("-", "")+ "." + ext;
//				// 文件全称
//				String filePath = path + File.separator +picName;
//				//临时文件全称
//				String tempFilePath = tempPath + File.separator +picName;
//				try {
//					//上传临时文件
//					byte[] data = pictureData.getData();
//					FileOutputStream out = new FileOutputStream(tempFilePath);
//					out.write(data);
//					out.close();
//					
//					File tmpFile = new File(tempFilePath);  
//					long size = tmpFile.length();
//					
//					if(pictrueMaxPixel > 0){
//						double scale = 1.0d ;
//						if(size > pictrueMaxPixel){
//							if(size > 0){
//				                scale = Float.parseFloat(String.valueOf(pictrueMaxPixel))/ size  ;
//				            }
//						}
//						if(size < pictrueMaxPixel){
//			                Thumbnails.of(tempFilePath).scale(1f).outputFormat(ext).toFile(filePath);
//			            }else{
//			                Thumbnails.of(tempFilePath).scale(1f).outputQuality(scale).outputFormat(ext).toFile(filePath);
//			            }
//					}else{
//						Thumbnails.of(tempFilePath).scale(1f).outputFormat(ext).toFile(filePath);
//					}
//					
//					FileUtils.delete(new File(tempFilePath));
//					
//					/*byte[] data = pictureData.getData();
//					FileOutputStream out = new FileOutputStream(filePath);
//					out.write(data);
//					out.close();*/
//				} catch (Exception ex) {
//					log.error("上传图片失败", ex);
//				}
//				tempUploadPath.add(picName);
//				uploadPathMap.put(entry.getKey(), tempUploadPath);
//			}
//		}
//		for (Map.Entry<String, List<HSSFPictureData>> entry : pictureMap.entrySet()) {	
//			log.info("key:" + entry.getKey() + " value:" + entry.getValue());
//		}
//		return uploadPathMap;  
//	}
//	
//	/**
//	 * @Title: deletePicture?
//	?* @Description: 删除已上传图片
//	?* @param uploadPathMap 设定参数?
//	?* @return void 返回类型?
//	 */
//	private void deletePicture(Map<String, List<String>> uploadPathMap){
//		for(Map.Entry<String, List<String>> entry : uploadPathMap.entrySet()){
//			for(String path: entry.getValue()){
//				FileUtils.delete(new File(path));
//			}
//		}
//	}
//	
//	/**
//	 * 获取导入数据列表
//	 * @param cls 导入对象类型
//	 * @param groups 导入分组
//	 */
//	public <E> Map<String, Object> getDataList(Class<E> cls,Integer maxRowNum, String imgUploadPath, int... groups) throws InstantiationException, IllegalAccessException{
//		List<Object[]> annotationList = new ArrayList<Object[]>();
//		// Get annotation field 
//		Field[] fs = cls.getDeclaredFields();
//		for (Field f : fs){
//			ExcelField ef = f.getAnnotation(ExcelField.class);
//			if (ef != null && (ef.type()==0 || ef.type()==2)){
//				if (groups!=null && groups.length>0){
//					boolean inGroup = false;
//					for (int g : groups){
//						if (inGroup){
//							break;
//						}
//						for (int efg : ef.groups()){
//							if (g == efg){
//								inGroup = true;
//								annotationList.add(new Object[]{ef, f});
//								break;
//							}
//						}
//					}
//				}else{
//					annotationList.add(new Object[]{ef, f});
//				}
//			}
//		}
//		// Get annotation method
//		Method[] ms = cls.getDeclaredMethods();
//		for (Method m : ms){
//			ExcelField ef = m.getAnnotation(ExcelField.class);
//			if (ef != null && (ef.type()==0 || ef.type()==2)){
//				if (groups!=null && groups.length>0){
//					boolean inGroup = false;
//					for (int g : groups){
//						if (inGroup){
//							break;
//						}
//						for (int efg : ef.groups()){
//							if (g == efg){
//								inGroup = true;
//								annotationList.add(new Object[]{ef, m});
//								break;
//							}
//						}
//					}
//				}else{
//					annotationList.add(new Object[]{ef, m});
//				}
//			}
//		}
//		// Field sorting
//		Collections.sort(annotationList, new Comparator<Object[]>() {
//			public int compare(Object[] o1, Object[] o2) {
//				return new Integer(((ExcelField)o1[0]).sort()).compareTo(
//						new Integer(((ExcelField)o2[0]).sort()));
//			};
//		});
//		//log.debug("Import column count:"+annotationList.size());
//		// Get excel data
//		
//		//统计有数据的行数（如果某一行数据为空，则以该行上一行为结尾，该空行之后的数据不做处理）
//		StringBuilder errorMsg = new StringBuilder();
//		int dataRow = 0;//用于记录数据总行数
//		boolean emptyRowFlag = true;//空行标志
//		for (int i = this.getDataRowNum(); i <= this.getLastDataRowNum(); i++) {
//			HSSFRow row = this.getRow(i);
//			int column = 0;
//			emptyRowFlag = true;
//			for (Object[] os : annotationList){
//				Object val = this.getCellValue(row, column++, os[1]);
//				if (StringUtils.isNotBlank(val.toString())) {
//					dataRow ++;
//					emptyRowFlag = false;
//					break;
//				}
//			}
//			//如果该行为空，则该行之后的数据不再处理
//			if(emptyRowFlag) {
//				break;
//			}
//		}
//	
//		//如果导入excel条数有限制，要判断条数是否超出限制
//		if (maxRowNum != null && maxRowNum > 0) {
//			if (dataRow > maxRowNum) {
//				errorMsg.append("导入数据条数[" + dataRow + "]超出最大限制[" + maxRowNum + "]");
//				Map<String, Object> map = new HashMap<String, Object>();
//				map.put("errorMsg", errorMsg.toString());
//				map.put("overMaxRowNum", errorMsg.toString());
//				return map;
//			}
//		}
//		
//		// 读取xls图片
//		Map<String, List<HSSFPictureData>> pictureMap = this.findAllPictureData(sheet);
//		
//		// 图片文件名list，用于存储需要删除的图片，待优化
//		List<String> picNameList = new ArrayList<String>();
//		
//		// 上传图片并返回路径
//		//Map<String, List<String>> uploadPathMap = this.uploadPicture(pictureMap);
//		
//		List<E> dataList = new ArrayList<E>();
//		boolean errorRowFlag = false;
//		
//		for (int i = this.headerNum; i <= dataRow; i++) {
//			E e = (E)cls.newInstance();
//			int column = 0;
//			HSSFRow row = this.getRow(i);
//			StringBuilder sb = new StringBuilder();
//			errorRowFlag = false;
//			
//			//判断行是否为空
//			for (Object[] os : annotationList){
//				Object val = this.getCellValue(row, column++, os[1]);
//				ExcelField ef = (ExcelField)os[0];
//				String valStr = val.toString();
//				
//				//首行字段校验
//				if (i == this.headerNum ) {
//					if (StringUtils.isBlank(valStr.replaceAll("\n", ""))) {
//						StringBuilder fileChangeMsg = new StringBuilder();
//						fileChangeMsg.append("模板已被改动，请重新下载模板");
//						Map<String, Object> map = new HashMap<String, Object>();
//						map.put("errorMsg", fileChangeMsg.toString());
//						return map;
//					} else if (!(valStr.replaceAll("\n", "")).contains(ef.title())){
//						StringBuilder fileChangeMsg = new StringBuilder();
//						fileChangeMsg.append("模板已被改动，请重新下载模板");
//						Map<String, Object> map = new HashMap<String, Object>();
//						map.put("errorMsg", fileChangeMsg.toString());
//						return map;
//					}
//				} else {
//					// 如果是图片字段，则赋值图片路径地址
//					if("1".equals(ef.isPicture())){
//						//上传图片
//						List<HSSFPictureData> hssfPictureDataList = new ArrayList<HSSFPictureData>();
//						hssfPictureDataList = pictureMap.get(i+"|"+(column-1));
//						if(StringUtils.isNotBlank(ef.notEmpty()) && "1".equals(ef.notEmpty())){
//							if(CollectionUtils.isEmpty(hssfPictureDataList)){
//								errorMsg.append("第" + (i + 1) + "行" + " 第" + (column-1) + "列  " + ef.title() + "图片不能为空<br/>");
//								errorRowFlag = true;
//							}
//						}
//						
//						if(!CollectionUtils.isEmpty(hssfPictureDataList)){
//							if(hssfPictureDataList.size() > ef.pictureMaxNumber()){
//								errorMsg.append("第" + (i + 1) + "行" + " 第" + (column-1) + "列  "+ ef.title() + "图片数量不能超过"+ ef.pictureMaxNumber() +"张<br/>");
//								errorRowFlag = true;
//							}
//							
//							//判断图片格式
//							for(HSSFPictureData hssfPictureDataObj : hssfPictureDataList){
//								// 获取图片格式
//								String ext = hssfPictureDataObj.suggestFileExtension();
//								List<String> pictureFormatList = Arrays.asList(ef.pictureFormat());
//								
//								if(!errorRowFlag && !pictureFormatList.contains(ext)){
//									errorMsg.append("第" + (i + 1) + "行" + " 第" + (column-1) + "列  "+ ef.title() + "格式错误，只能上传"+ pictureFormatList.toString() +"格式图片<br/>");
//									errorRowFlag = true;
//								}
//							}
//							
//							if(!errorRowFlag){
//								Map<String, List<HSSFPictureData>> uploadMap = new HashMap<String, List<HSSFPictureData>>();
//								uploadMap.put(i+"|"+(column-1), pictureMap.get(i+"|"+(column-1)));
//								Map<String, List<String>> uploadPathMap = this.uploadPicture(uploadMap,ef.pictrueMaxPixel(),imgUploadPath);
//								val = uploadPathMap.get(i+"|"+(column-1));//key 为 行+列
//								valStr = val.toString();
//							}
//						}
//					}
//					
//					if (StringUtils.isBlank(valStr) && !"1".equals(ef.isPicture())) {
//						if (StringUtils.isNotBlank(ef.notEmpty()) && "1".equals(ef.notEmpty())) {
//							errorMsg.append("第" + (i + 1) + "行" + " 第" + (column-1) + "列  "+ ef.title() + "不能为空<br/>");
//							errorRowFlag = true;
//						}
//					}
//					
//					if(!StringUtils.isBlank(valStr)){
//						if (StringUtils.isNotBlank(ef.pattern())) {
//							String regexp = ImportExcel.getPatternLabel(valStr, ef.pattern(), "regexp", "");
//							String msg = ImportExcel.getPatternLabel(valStr, ef.pattern(), "msg", "");
//							if (StringUtils.isNotBlank(regexp) && StringUtils.isNotBlank(msg) && !Pattern.matches(regexp, valStr)) {
//								errorMsg.append("第" + (i +1) + "行" + " 第" + (column-1) + "列  "+ ef.title() + msg + "<br/>");
//								errorRowFlag = true;
//							}
//						}
//						
//						if(ef.valueLength() > 0){
//							if(!validateInputLength(valStr,ef.valueLength())){
//								errorMsg.append("第" + (i +1) + "行"+ " 第" + (column-1) + "列  " + ef.title() + "超过长度限制<br/>");
//								errorRowFlag = true;
//							}
//						}
//					}
//							
//					if (StringUtils.isNotBlank(valStr)){
//						
//						// If is dict type, get dict value
//						if (this.engFlag) {
//							if (StringUtils.isNotBlank(ef.dictEn())){
//								val = ImportExcel.getDictLabel(val == null ? "" : val.toString(), ef.dictEn(), "");
//							}
//						} else {
//							if (StringUtils.isNotBlank(ef.dict())){
//								val = ImportExcel.getDictLabel(val == null ? "" : val.toString(), ef.dict(), "");
//							}
//						}
//						Class<?> valType = Class.class;
//						if (os[1] instanceof Field){
//							valType = ((Field)os[1]).getType();
//						}else if (os[1] instanceof Method){
//							Method method = ((Method)os[1]);
//							if ("get".equals(method.getName().substring(0, 3))){
//								valType = method.getReturnType();
//							}else if("set".equals(method.getName().substring(0, 3))){
//								valType = ((Method)os[1]).getParameterTypes()[0];
//							}
//						}
//						// Get param type and type cast
//						
//						//valType = ImportExcel.getFieldClassType(os[1]);
//						//log.debug("Import value type: ["+i+","+column+"] " + valType);
//						try {
//							if (valType == String.class){
//								String s = String.valueOf(val.toString());
//								if(StringUtils.endsWith(s, ".0")){
//									val = StringUtils.substringBefore(s, ".0");
//								}else{
//									val = String.valueOf(val.toString());
//								}
//							}else if (valType == Integer.class){
//								val = Double.valueOf(val.toString()).intValue();
//							}else if (valType == Long.class){
//								val = Double.valueOf(val.toString()).longValue();
//							}else if (valType == Double.class){
//								val = Double.valueOf(val.toString());
//							}else if (valType == Float.class){
//								val = Float.valueOf(val.toString());
//							}else if (valType == Date.class){
//								val = DateUtil.getJavaDate((Double)val);
//							}else if (valType == BigDecimal.class){
//								val = new BigDecimal(val.toString());
//							}else{
//								if (ef.fieldType() != Class.class){
//									val = ef.fieldType().getMethod("getValue", String.class).invoke(null, val.toString());
//								}else{
//									val = Class.forName(this.getClass().getName().replaceAll(this.getClass().getSimpleName(), 
//											"fieldtype."+valType.getSimpleName()+"Type")).getMethod("getValue", String.class).invoke(null, val.toString());
//								}
//							}
//						} catch (Exception ex) {
//							log.info("Get cell value ["+i+","+column+"] error: " + ex.toString());
//							val = null;
//						}
//						// set entity value
//						if (os[1] instanceof Field){
//							Reflections.invokeSetter(e, ((Field)os[1]).getName(), val);
//						}else if (os[1] instanceof Method){
//							String mthodName = ((Method)os[1]).getName();
//							if ("get".equals(mthodName.substring(0, 3))){
//								mthodName = "set"+StringUtils.substringAfter(mthodName, "get");
//							}
//							Reflections.invokeMethod(e, mthodName, new Class[] {valType}, new Object[] {val});
//						}
//					}
//					sb.append(val+", ");
//				}
//				
//
//			}
//			//该行数据没有问题，并不不是首行（首行是字段不是数据）才会放入list中
//			if (!errorRowFlag && i != this.headerNum ) {
//				dataList.add(e);
//				
//			}
//			log.debug("Read success: ["+i+"] "+sb.toString());
//		}
//		//return dataList;
//		Map map = new HashMap();
//		map.put("data", dataList);
//		map.put("errorMsg", errorMsg.toString());
//		map.put("rowCount", dataRow);
//		return map;
//	}
//	
//	/**
//	 * 获取注解dict替换值
//	 */
//	public static String getDictLabel(String value, String type, String defaultValue){
//		if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(value)){
//			Map<String, String> dictMap=new HashMap<String, String>();
//			String[] dictArray=type.split(";");
//			for(int i=0;i<dictArray.length;i++){
//				int index=dictArray[i].indexOf("=");
//				//dictMap.put(dictArray[i].substring(0, index), dictArray[i].substring(index+1,dictArray[i].length()));
//				dictMap.put(dictArray[i].substring(index+1,dictArray[i].length()).trim(), dictArray[i].substring(0, index).trim());
//			}
//			return dictMap.get(value);
//		}
//		return defaultValue;
//	}
//	
//	/**
//	 * 获取pattern中的值
//	 * @param value
//	 * @param pattern
//	 * @param key regexp或者msg
//	 * @param defaultValue
//	 * @return
//	 */
//	public static String getPatternLabel(String value, String pattern, String key, String defaultValue) {
//		if (StringUtils.isNotBlank(value) && StringUtils.isNotBlank(pattern)) {
//			if (pattern.indexOf("regexp=")>=0 && pattern.indexOf("msg=")>=0 && pattern.indexOf(";")>0) {
//				Map<String, String> patternMap = new HashMap<String, String>();
//				String[] ptnArr = pattern.split(";");
//				for (int i = 0; i<ptnArr.length; i++) {
//					int equal = ptnArr[i].indexOf("=");
//					patternMap.put(ptnArr[i].substring(0, equal).trim(), ptnArr[i].substring(equal+1).trim());
//				}
//				return patternMap.get(key);
//			}
//		}
//		return defaultValue;
//	}
//	
//	public static Class<?> getFieldClassType(Object os){
//		Class<?> valType = Class.class;
//		if (os instanceof Field){
//			valType = ((Field)os).getType();
//		}else if (os instanceof Method){
//			Method method = ((Method)os);
//			if ("get".equals(method.getName().substring(0, 3))){
//				valType = method.getReturnType();
//			}else if("set".equals(method.getName().substring(0, 3))){
//				valType = ((Method)os).getParameterTypes()[0];
//			}
//		}
//		return valType;
//	}
//
//	/**
//	 * 导入测试
//	 */
//	/*public static void main(String[] args) throws Throwable {
//		
//		ImportExcel ei = new ImportExcel("F:/轮询商家导入excel/importPollingMerchant.xls", 0);
//		
//		List<PollMercahntForm> orderList = new ArrayList<PollMercahntForm>();
//		
//		Map map = ei.getDataList(OrderExcelForm.class, new int[]{2});
//		orderList = (List<PollMercahntForm>) map.get("data");
//		String errorMsg = (String) map.get("errorMsg");
//		
//		System.out.println(orderList.size());
//		
//		for (int i = 0; i<orderList.size(); i++) {
//			OrderExcelForm or = orderList.get(i);
//			System.out.println(or.getOrderId());
//		}
//		
//		for (int i = ei.getDataRowNum(); i < ei.getLastDataRowNum(); i++) {
//			Row row = ei.getRow(i);
//			for (int j = 0; j < ei.getLastCellNum(); j++) {
//				Object val = ei.getCellValue(row, j);
//				System.out.print(val+", ");
//			}
//			System.out.print("\n");
//		}
//		
//	}*/
//	
//	/**
//	 * 验证字符串长度，如果超过设计长度将返回false
//	 * 
//	 */
//	private boolean validateInputLength(String input, int length) {
//		int l = 0;
//		if (StringUtils.isNotBlank(input)) {
//			for (int i = 0; i < input.length(); i++) {
//				int ascii = Character.codePointAt(input, i);
//				if (ascii >= 0 && ascii <= 255) {
//					l++;
//				} else {
//					l += 2;
//				}
//			}
//			return l <=  length;
//		} else {
//			return false;
//		}
//	}
//
//}
