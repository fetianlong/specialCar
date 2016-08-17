package com.huatai.special.util.tag;

import java.util.Iterator;
import java.util.List;

import javax.servlet.jsp.JspWriter;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.tags.RequestContextAwareTag;

import com.huatai.special.model.Dictionary;
import com.huatai.special.service.DictionaryService;

public class DictionaryTag extends RequestContextAwareTag {
	private static final long serialVersionUID = 1L;
	
	
	private String parentCode;
	private String code;

//	@Autowired
//	private DictionaryService dictionaryService;
	
	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	protected int doStartTagInternal() throws Exception {
		JspWriter out = super.pageContext.getOut();
		try {
			StringBuffer sb = new StringBuffer();
			ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(pageContext.getServletContext());
			DictionaryService dictionaryService = (DictionaryService)ctx.getBean("dictionaryService");
			//根据父级code获取数据
			if(null != parentCode && !"".equals(parentCode)){
				sb.append("<select id='dictionaryId' name='dictionaryId' class='control-label'>");
				sb.append("<option value=''>--请选择--</option>");
				
				List<Dictionary> dictionaryList = dictionaryService.getListDictionary(parentCode);
				if(dictionaryList.size()>0){
					for (Iterator iterator = dictionaryList.iterator(); iterator.hasNext();) {
						Dictionary dictionary = (Dictionary) iterator.next();
						sb.append("<option value='"+dictionary.getId()+"'>"+dictionary.getName()+"</option>");
					}
				}
				sb.append("</select>");
			}
			if(null != code && !"".equals(code)){
				Dictionary dic = dictionaryService.getDictionaryByCode(code);
				sb.append(dic.getName());
			}
			
			out.println(sb);
//			out.println("<h3>年龄：" + age + "</h3>");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return EVAL_PAGE; // 表示处理完标签后继续执行以下的JSP网页  
	  // return SKIP_PAGE; //表示不处理接下来的JSP网页  
	}
}
