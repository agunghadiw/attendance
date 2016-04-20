<%@ taglib prefix="s" uri="/struts-tags"%>


    <!--  notification start -->
    <ul class="nav top-menu">
        <!-- settings start -->
        <s:if test="(employeeTaskList!=null)">
        <li class="dropdown">
            <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                <i class="fa fa-tasks"></i>
                <span class="badge bg-success"><s:property value="employeeTaskList.size()"/></span>
            </a>
            <ul class="dropdown-menu extended tasks-bar">
                <li>
                    <p class="">Anda memiliki <s:property value="employeeTaskList.size()"/> tugas tertunda.</p>
                </li>
                
                <s:if test="(employeeTaskList.size()>4)">
                <s:iterator value="employeeTaskList" begin="0" end="3" >
                <li>
                    <a href='..<s:property value="link"/>?id=<s:property value="id"/>&processId=<s:property value="processId"/>'>
                        <div class="task-info clearfix">
                            <div class="desc pull-left">
                                <h6><s:property value="processName"/></h6>
                                <h6>(<s:property value="fullName"/>)</h6>
                                <p><s:date name="processDate" format="dd MMM yyyy HH:mm"/></p>
                            </div>
                                    
                        </div>
                    </a>
                </li>
                </s:iterator>
                </s:if>
                <s:else>
                <s:iterator value="employeeTaskList" >
                <li>
                    <a href='..<s:property value="link"/>?id=<s:property value="id"/>&processId=<s:property value="processId"/>'>
                        <div class="task-info clearfix">
                            <div class="desc pull-left">
                                <h6><s:property value="processName"/></h6>
                                <h6>(<s:property value="fullName"/>)</h6>
                                <p><s:date name="processDate" format="dd MMM yyyy HH:mm"/></p>
                            </div>
                        </div>
                    </a>
                </li>
                </s:iterator>
                </s:else>
                

                <li class="external">
                    <a href="<s:url value="/hr/EmployeeTaskListAction_list.action"/>"><b>Lihat Semua Task</b></a>
                </li>
            </ul>
        </li>
        <!-- settings end -->
        </s:if>
        
        <s:if test="(attendanceMachineLists!=null)">
        <!-- notification dropdown start-->
        <li id="header_notification_bar" class="dropdown">
            <a data-toggle="dropdown" class="dropdown-toggle" href="#">

                <i class="fa fa-bell-o"></i>
                <span class="badge bg-warning"><s:property value ="attendanceMachineLists.size()"/></span>
            </a>
            <ul class="dropdown-menu extended notification">
                <li>
                    <p>Peringatan</p>
                </li>
                <s:iterator value="attendanceMachineLists">
                <li>
                    <div class="alert alert-danger clearfix">
                        <span class="alert-icon"><i class="fa fa-bolt"></i></span>
                        <div class="noti-info">
                            <a href="#"> Service Machine ID <s:property value="machineId"/> down.</a>
                        </div>
                    </div>
                </li>
                </s:iterator>
                

            </ul>
        </li>
        </s:if>
        <!-- notification dropdown end -->
    </ul>
    <!--  notification end -->


