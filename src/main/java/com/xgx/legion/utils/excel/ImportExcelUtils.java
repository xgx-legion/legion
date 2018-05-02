//package com.xgx.legion.utils.excel;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//
//import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//public class ImportExcelUtils {
//	
//	private static Logger log = LoggerFactory.getLogger(ImportExcelUtils.class);
//	
//	
//	/**
//	 * 注：如果excel文件中如果中间存在空行，则以该空行上一行为结尾行，该空行之后的数据不再导入
//	 * @param file file excel文件对象
//	 * @param excelFrom  excelFrom excel对应的带有注解的form对象
//	 * @param maxRowNum 允许导入excel最大数据条数  ，null或者0表示不限制
//	 * @param groups   分组,无需分组传null
//	 * @return 返回map，通过key[data],可以得到list<E>，通过key[errorMsg] 可以获取错误提示信息，通过key[rowCount] 获取数据总条数,key[overMaxRowNum]超出设置的最大条数时该值不为空
//	 */
//	public static <E> Map<String, Object> importExcel (File file, Class<E> excelFrom,Integer maxRowNum, String imgUploadPath, int... groups) throws InvalidFormatException, IOException, InstantiationException, IllegalAccessException{
//		ImportExcel ei = new ImportExcel(file, 0);
//		
//		Map<String, Object> map = new HashMap<String, Object>();
//		
//		map = (Map) ei.getDataList(excelFrom, maxRowNum, imgUploadPath, groups);
//		
//		return map;
//	}
//}
