<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

  <div class="headers">
     <div class="logo_pic"><a href="${ctx}/"><img src="${ctx}/static/hawtai_pc/imgs/logo1.png"></a></div>
     <div class="home_pic"><a href="${ctx}/"><img src="${ctx}/static/hawtai_pc/imgs/homepic.png"></a></div>
      <div class="herd_right">
       <div class="rig_text"><shiro:principal /> 欢迎登录！</div>
<!--        <div class="rig_text">上次登录：【 2016年6月20日  星期一 】</div> -->
       <div class="rig_text"><a href="#"><span><img src="${ctx}/static/hawtai_pc/imgs/xiu.png"></span>修改密码</a></div>
       <div class="rig_text"><a href="${ctx}/login/logout"><span><img src="${ctx}/static/hawtai_pc/imgs/none.png"></span>安全退出</a></div>
      </div>
   </div>	
