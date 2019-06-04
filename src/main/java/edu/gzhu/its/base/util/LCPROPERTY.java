package edu.gzhu.its.base.util;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.ResourceFactory;

/**
 * 本体通用语义属性
 * @author : 丁国柱
 */
public class LCPROPERTY {
	protected static Model model = ModelFactory.createDefaultModel();
	protected static final String uri = "http://www.edutupu.com/owl/property#";

	protected static final Resource resource(String local) {
		return ResourceFactory.createResource(uri + local);
	}

	protected static final Property property(String local) {
		return ResourceFactory.createProperty(uri, local);
	}

	// 代替rdfs.label
	public static final Property label = property( "label");
	
	//描述信息
	public static final Property description = property( "description");
	
	//有实例，用于表示文字类型的实例，如一个排比句等长文本类型的实例，解决有空格情况下导入实例出现数据库损坏的问题
	public static final Property hasInstance = property( "hasInstance");
	
	//例子
	public static final Property example = property( "example");
	
	//内容
	public static final Property content = property( "content");
	
	//出处
	public static final Property from = property( "出处");
	
	//解析
	public static final Property jiexi = property( "解析");
	
	//情感极性（褒义、贬义、中性、兼有褒贬两义）
	public static final Property emotionalPolarity = property( "情感极性");
	
	//情感
	public static final Property emotional= property( "情感");
	
	//关联信息
	public static final Property linkedInfo = property( "linkedInfo");
	
	
	//关联课程
	public static final Property linkedCourse = property( "linkedCourse");
	
	/*关联学习元
	 */
	public static final Property linkedKo = property( "linkedKo");
	
	public static final Property TeachingObjecty= property( "教学目标");

	public static String getURI() {
		return uri;
	}
}
