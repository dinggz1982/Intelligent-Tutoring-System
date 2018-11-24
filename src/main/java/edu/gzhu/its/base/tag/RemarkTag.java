package edu.gzhu.its.base.tag;

import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.context.annotation.Configuration;

@Configuration
public class RemarkTag extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8455353130297004825L;

	public static String getContentRelated(String contentRelated) {

		if (contentRelated == null || contentRelated.length() == 0) {
			return "无";
		} else {
			String content = "";
			if (contentRelated.indexOf("1") != -1) {
				content = content + "针对课程内容的提问" + ",";
			}
			if (contentRelated.indexOf("2") != -1) {
				content = content + "针对课程内容的评论与观点" + ",";
			}
			if (contentRelated.indexOf("3") != -1) {
				content = content + "课程内容的相关资源说明" + ",";
			}
			return content;
		}
	}

	public static String getEmotionRelated(String emotionRelated) {

		if (emotionRelated == null || emotionRelated.length() == 0) {
			return "无";
		} else {
			String emotion = "";
			if (emotionRelated.indexOf("1") != -1) {
				emotion = emotion + "对课程内容的情感倾向" + ",";
			}
			if (emotionRelated.indexOf("2") != -1) {
				emotion = emotion + "对教师或教学风格的情感倾向" + ",";
			}
			return emotion;
		}
	}
	
	public static String getOtherRelated(String otherRelated) {

		if (otherRelated == null || otherRelated.length() == 0) {
			return "无";
		} else {
			String other = "";
			if (otherRelated.indexOf("1") != -1) {
				other = other + "对网络课程技术支持的问题或评价" + ",";
			}
			if (otherRelated.indexOf("2") != -1) {
				other = other + "社交类评论 " + ",";
			}
			if (otherRelated.indexOf("3") != -1) {
				other = other + "对学习状态或学习进度的描述" + ",";
			}
			if (otherRelated.indexOf("4") != -1) {
				other = other + "其他不属于上述类别的评论" + ",";
			}
			return other;
		}
	}
	

}
