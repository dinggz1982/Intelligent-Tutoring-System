package edu.gzhu.its.jena;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.jena.query.Dataset;
import org.apache.jena.query.ReadWrite;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.tdb.TDBFactory;
import org.apache.jena.vocabulary.OWL;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.RDFS;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import edu.gzhu.its.base.util.LCPROPERTY;
import edu.gzhu.its.humanism.entity.ChineseCharacters;
import edu.gzhu.its.humanism.entity.Spell;
import edu.gzhu.its.humanism.service.IChineseCharactersService;



/**
 * 导入汉字内容
 * 
 * @author : 丁国柱
 * @date : 2014-10-11 下午7:54:06
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ImportHANZI {

	@Autowired
	private IChineseCharactersService chineseCharactersService;


	@Test
	public void test() throws SQLException {

		String url = "http://www.edutupu.com/ontologies/chinese/hanzi#";
		String hanziUrl = "http://www.edutupu.com/ontologies/chinese/hanzi#汉字";
		String ziyi = "http://www.edutupu.com/ontologies/chinese/hanzi#字义";
		String pyin = "http://www.edutupu.com/ontologies/chinese/hanzi#拼音";
		String shengdiao = "http://www.edutupu.com/ontologies/chinese/hanzi#声调";
		String bushou = "http://www.edutupu.com/ontologies/chinese/hanzi#部首";
		String bihuashu = "http://www.edutupu.com/ontologies/chinese/hanzi#笔画数";
		
		String directory = "e:/edutupu" ;
		  Dataset dataset = TDBFactory.createDataset(directory) ;
		dataset.begin(ReadWrite.WRITE);
		
		Model model = dataset.getDefaultModel();
		Resource hanziResource = model
				.getResource(hanziUrl);
		model.add(hanziResource, RDFS.label, "汉字");

		String description = "汉字，记录汉语的文字。中华人民共和国官方文字，新加坡的官方文字。亦称中文字、中国字、国字，是汉字文化圈（中国、日本、朝鲜半岛、东亚及东南亚部分地区）广泛或曾使用的一种文字，属于表意文字的词素音节文字，是上古时代由汉族人所发明并作改进。起源史漫长，初步成熟定型的汉字系统为公元前1300年商朝的甲骨文，再是金文，然后到秦朝的小篆、隶书，发展至汉朝才被取名为“汉字”，至唐代楷化为今日所用的手写字体标准——楷书。汉字是中国迄今为止连续使用时间最长的主要文字，中国历代皆以汉字为主要官方文字。汉字的演变过程是：从语段文字发展到语词文字；发源期有7762年（±128年）前的贾湖刻符、及双墩刻符、半坡陶符、庄桥坟遗址文字、大汶口陶尊符号、骨刻文、尧舜时代陶寺遗址朱文、夏代水书；成熟期有甲骨文、金文、大篆、小篆、籀文、隶书、楷书、草书、行书。";

		model.add(hanziResource, LCPROPERTY.description, description);
		// 拼音
		Property pyinProperty = model.createProperty(pyin);
		model.add(pyinProperty, RDF.type, OWL.DatatypeProperty);// 数据类型

		// 声调
		Property shengdiaoProperty = model.createProperty(shengdiao);
		model.add(shengdiaoProperty, RDF.type, OWL.DatatypeProperty);// 数据类型

		// 部首
		Property bushouProperty = model.createProperty(bushou);
		model.add(bushouProperty, RDF.type, OWL.DatatypeProperty);// 数据类型

		// 笔画数
		Property bihuashuProperty = model.createProperty(bihuashu);
		model.add(bihuashuProperty, RDF.type, OWL.DatatypeProperty);// 数据类型

		// 创建字义属性
		Property ziyiProperty = model.createProperty(ziyi);
		model.add(ziyiProperty, RDF.type, OWL.DatatypeProperty);// 数据类型

		List<ChineseCharacters> hanzis = this.chineseCharactersService.findAll();
		for (Iterator iterator = hanzis.iterator(); iterator.hasNext();) {
			ChineseCharacters hanzi = (ChineseCharacters) iterator.next();

			Resource hanziInstanceResource = model
					.getResource(url + hanzi.getHanzi());

			model.add(hanziInstanceResource, RDF.type, hanziResource);// 创建汉字
			model.add(hanziInstanceResource, RDFS.label, "字");// 创建汉字
			model.add(hanziInstanceResource, RDFS.label, "汉字");// 创建汉字
			model.add(hanziInstanceResource, RDFS.label, hanzi.getHanzi());// 创建汉字

			// 增加字的描述
			if (hanzi.getAnalysis() != null && hanzi.getAnalysis().length() > 0) {
				if (hanzi.getDemonstration() != null
						&& hanzi.getDemonstration().indexOf(".swf") != -1) {
					model.add(
							hanziInstanceResource,
							LCPROPERTY.description,
							"<div> style='text-align:center;'><object codebase='http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=7,0,19,0' width='500' height='400'>"
									+ "<param name='movie' value='/HanziFiles/WordSwf/"
									+ hanzi.getHanzi()
									+ ".swf'>"
									+ "<param name='WMode' value='Transparent'>"
									+ "<param name='quality' value='high'> "
									+ "<embed src='/HanziFiles/WordSwf/"
									+ hanzi.getHanzi()
									+ ".swf' quality='high' wmode='transparent' pluginspage='http://www.macromedia.com/go/getflashplayer' type='application/x-shockwave-flash' width='500' height='400'></object> </div> "
									+"<div>" +hanzi.getAnalysis()+"</div>");
					System.out.println("增加了Analysis1");
				} else {
					model.add(hanziInstanceResource, LCPROPERTY.description,
							"<div>" + hanzi.getAnalysis() +"</div>");
					System.out.println("增加了Analysis2");
				}
			}
			// model.add(hanziResource, RDF.type, RDFS.Class);
			// System.out.println(hanziResource.toString());
			// 字义

			if (hanzi.getDetailExplain() != null
					&& hanzi.getDetailExplain().length() > 0) {
				model.add(hanziInstanceResource, ziyiProperty, hanzi.getDetailExplain());
				System.out.println("增加了DetailExplain");
			}

			Set<Spell> spells = hanzi.getSpells();
			for (Iterator<Spell> iterator2 = spells.iterator(); iterator2
					.hasNext();) {
				Spell spell = iterator2.next();
				model.add(hanziInstanceResource, pyinProperty, spell.getSpell());
				if (spell.getTone() == 1) {
					model.add(hanziInstanceResource, shengdiaoProperty, "第一声(阴平)");
				}
				if (spell.getTone() == 0) {
					model.add(hanziInstanceResource, shengdiaoProperty, "轻声");
				}
				if (spell.getTone() == 2) {
					model.add(hanziInstanceResource, shengdiaoProperty, "第二声(阳平)");
				}
				if (spell.getTone() == 3) {
					model.add(hanziInstanceResource, shengdiaoProperty, "第三声(上声)");
				}
				if (spell.getTone() == 4) {
					model.add(hanziInstanceResource, shengdiaoProperty, "第四声(去声)");
				}
			}

			// 部首
			if (hanzi.getRadical() != null && hanzi.getRadical().length() > 0) {
				model.add(hanziInstanceResource, bushouProperty, hanzi.getRadical());
			}

			// 笔画数
			if (hanzi.getStrokeNum() > 0) {
				model.add(hanziInstanceResource, bihuashuProperty,
						String.valueOf(hanzi.getStrokeNum()));
			}
		//	dataset.commit();
		}
		model.commit();
		dataset.end();
	}
	
	@Test
	public void query(){
		String directory = "e:/edutupu" ;
		  Dataset dataset = TDBFactory.createDataset(directory) ;
		dataset.begin(ReadWrite.READ);
		
		Model model = dataset.getDefaultModel();
		StmtIterator iterator =  model.listStatements();
		while(iterator.hasNext()){
			Statement statement =  iterator.next();
			System.out.println(statement.getSubject() + "," + statement.getPredicate() + "," + statement.getObject());
		}
		
	}

}
