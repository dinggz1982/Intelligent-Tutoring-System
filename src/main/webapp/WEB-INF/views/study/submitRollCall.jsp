<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>课堂点名</title>
   <%@include file="/WEB-INF/views/include/top.jsp" %>
  </head>
  <body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 课堂点名 </nav>
<div class="pd-20" style="text-align: center;margin: 0 auto; ">
  
  <form action="submitRollCall" method="get">
    <table class="table table-border table-bordered table-hover table-bg table-sort" style="width: 60%;text-align: center;margin: 0 auto; ">
  
  <tr>
    		<td style="text-align: center;">点名信息：</td>
    		<td colspan="2">
    											<input type="text" class="input-text valid" autocomplete="off" placeholder="点名信息" name="name" id="name" data-filtered="filtered">
    		
    		</td>
    	</tr>
    	
  <tr>
    		<td style="text-align: center;">指定学生：</td>
    		<td colspan="2">
				<select name="selectStu" id="selectStu"  onchange="selectStudent()">
					<c:forEach var="student" items="${allStudents }">
						<option value="${student[0] }">${student[1] }</option>
					</c:forEach>
				</select>    		
    		</td>
    	</tr>
    	</table>
  <table class="table table-border table-bordered table-hover table-bg table-sort" style="width: 60%;text-align: center;margin: 0 auto; ">
    <thead>
      <tr class="text-c">
        <th width="100">姓名</th>
        <th width="150">学号</th>
        <th width="70">状态</th>
      </tr>
    </thead>
    
    <tbody id="stubody">
    	<c:forEach items="${users }" var="user" varStatus="status">
    	<input type="hidden" name="id" value="${user.id }"/>
      <tr class="text-c">
        <td><u style="cursor:pointer" class="text-primary">${user.username }</u></td>
        <td>${user.xuehao }</td>
        <td class="user-status">
        <!-- 1.上课 2.迟到  3.早退 4.请假   5.旷课-->
        <select class="select valid" size="1" name="type" data-filtered="filtered">
										<option value="1" data-filtered="filtered">上课</option>
										<option value="2" data-filtered="filtered">迟到</option>
										<option value="3" data-filtered="filtered">早退</option>
										<option value="4" data-filtered="filtered">请假</option>
										<option value="5" data-filtered="filtered">旷课</option>
									</select>
        
        </td>
      </tr>
      </c:forEach>
    </tbody>
  </table>
  <p style="margin-top: 10px;">
  <input class="btn btn-primary" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;" data-filtered="filtered">
  </form>
</div>

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="lib/My97DatePicker/4.8/WdatePicker.js"></script> 
<script type="text/javascript" src="lib/datatables/1.10.0/jquery.dataTables.min.js"></script> 
<script type="text/javascript" src="lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript">
function selectStudent(){
	var s1 = $('#selectStu').val();
var s2 = $('#selectStu option:selected').text();
$('#stubody').append("<tr class='text-c'><input type='hidden' name='id' value='"+s1+"'/><td><u style='cursor:pointer' class='text-primary'>"+s2+"</u></td><td>"+111+"</td><td class='user-status'><select class='select valid' size='1' name='type' data-filtered='filtered'><option value='1' data-filtered='filtered'>上课</option><option value='2' data-filtered='filtered'>迟到</option><option value='3' data-filtered='filtered'>早退</option><option value='4' data-filtered='filtered'>请假</option><option value='5' data-filtered='filtered'>旷课</option></select></td></tr>").show();
}
</script>
  </body>
</html>
