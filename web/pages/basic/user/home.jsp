<% response.setHeader("Pragma", "No-cache");
response.setDateHeader("Expires", 0);
response.setHeader("Cache-Control", "no-cache");%>
<%@ taglib prefix="s" uri="/struts-tags"%>



<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">

    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="ThemeBucket">
    <link rel="shortcut icon" href="images/favicon.png">

    <title><s:text name="label.index"/></title>

    <!--Core CSS -->
    <link href="<s:url value="/css/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<s:url value="/css/bootstrap-reset.css"/>" rel="stylesheet">
    <link href="<s:url value="/font-awesome/css/font-awesome.css"/>" rel="stylesheet" />
    
    <link href="<s:url value="/js/fullcalendar/bootstrap-fullcalendar.css"/>" rel="stylesheet" />

    <!-- Custom styles for this template -->
    <link href="<s:url value="/css/style.css"/>" rel="stylesheet">
    <link href="<s:url value="/css/style-responsive.css"/>" rel="stylesheet" />

    <!-- Just for debugging purposes. Don't actually copy this line! -->
    <!--[if lt IE 9]>
    <script src="<s:url value="/js/ie8-responsive-file-warning.js"/>"></script><![endif]-->

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
</head>

<body>

<section id="container" >
<!--header start-->
<s:include value="/common/header.jsp"/>
<!--header end-->

<s:include value="/common/left_side.jsp"/>
<!--sidebar end-->
    <!--main content start-->
    <section id="main-content">
        <section class="wrapper">
        <!-- page start-->
			<div class="row">
    			<div class="col-md-12">
                <section class="panel">
                    <header class="panel-heading">
                        <s:property value="#session['permission_name']"/>
                        <span class="tools pull-right">
                            <a href="javascript:;" class="fa fa-chevron-down"></a>
                            <a href="javascript:;" class="fa fa-cog"></a>
                            <a href="javascript:;" class="fa fa-times"></a>
                         </span>
                    </header>
                    <div class="panel-body">
                        <div class="chartJS">
                            <canvas id="bar-chart-js" height="250" width="800" ></canvas>


                        </div>



                    </div>
                </section>
        		</div>
        	</div>
        	
        	<div class="row">
    			<div class="col-md-6">	
        		<section class="panel">
                    <header class="panel-heading">
                        Draggable Calendar
                        <span class="tools pull-right">
                            <a href="javascript:;" class="fa fa-chevron-down"></a>
                            <a href="javascript:;" class="fa fa-cog"></a>
                            <a href="javascript:;" class="fa fa-times"></a>
                         </span>
                    </header>
                    <div class="panel-body">
                        <!-- page start-->
                        <div class="row">
                            <aside class="col-lg-12">
                                  <div id="calendar" class="has-toolbar"></div>
                            </aside>
                            
                        </div>
                        <!-- page end-->
                    </div>
                </section>
                </div>
                
                <div class="col-md-6">	
			    <!--notification start-->
			    <section class="panel">
			        <header class="panel-heading">
			            Notification <span class="tools pull-right">
			            <a href="javascript:;" class="fa fa-chevron-down"></a>
			            <a href="javascript:;" class="fa fa-cog"></a>
			            <a href="javascript:;" class="fa fa-times"></a>
			            </span>
			        </header>
			        <div class="panel-body">

			            <s:if test="(employeeLeaveTaskList.size()>4)">
                	<s:iterator value="employeeLeaveTaskList" begin="0" end="4" >
			            <div class="alert alert-warning ">
			                <span class="alert-icon"><i class="fa fa-bell-o"></i></span>
			                <div class="notification-info">
			                    <ul class="clearfix notification-meta">
			                        <li class="pull-left notification-sender"><s:property value="fullName"/></li>
			                        <li class="pull-right notification-time"><s:date name="processDate" nice="true"/></li>
			                    </ul>
			                    <p>
			                        <s:property value="processName"/>
			                    </p>
			                </div>
			            </div>
			            </s:iterator>
			            </s:if>
			            <s:else>
                	<s:iterator value="employeeTaskList" >
                	<div class="alert alert-warning ">
			                <span class="alert-icon"><i class="fa fa-bell-o"></i></span>
			                <div class="notification-info">
			                    <ul class="clearfix notification-meta">
			                        <li class="pull-left notification-sender"><s:property value="fullName"/></li>
			                        <li class="pull-right notification-time"><s:date name="processDate" nice="true"/></li>
			                    </ul>
			                    <p>
			                        <s:property value="processName"/>
			                    </p>
			                </div>
			            </div>
                	</s:iterator>
                	</s:else>
			            
			        </div>
			    </section>
			    <!--notification end-->
			    </div>
			</div>
                
        
        <!-- page end-->
        </section>
    </section>
    <!--main content end-->
<!--right sidebar start-->
<s:include value="/common/right_side.jsp"/>
<!--right sidebar end-->

</section>

<!-- Placed js at the end of the document so the pages load faster -->

<!--Core js-->
<script src="<s:url value="/js/jquery.js"/>"></script>
<script src="<s:url value="/js/bootstrap.min.js"/>"></script>
<script class="include" type="text/javascript" src="<s:url value="/js/jquery.dcjqaccordion.2.7.js"/>"></script>
<script src="<s:url value="/js/jquery.scrollTo.min.js"/>"></script>
<script src="<s:url value="/js/jquery.slimscroll.min.js"/>"></script>
<script src="<s:url value="/js/jquery.nicescroll.js"/>"></script>

<script src="<s:url value="/js/fullcalendar/fullcalendar.min.js"/>"></script>

<!--Easy Pie Chart-->
<script src="<s:url value="/js/easypiechart/jquery.easypiechart.js"/>"></script>
<!--Sparkline Chart-->
<script src="<s:url value="/js/sparkline/jquery.sparkline.js"/>"></script>
<!--jQuery Flot Chart-->
<script src="<s:url value="/js/flot-chart/jquery.flot.js"/>"></script>
<script src="<s:url value="/js/flot-chart/jquery.flot.tooltip.min.js"/>"></script>
<script src="<s:url value="/js/flot-chart/jquery.flot.resize.js"/>"></script>
<script src="<s:url value="/js/flot-chart/jquery.flot.pie.resize.js"/>"></script>

<!--Chart JS-->
<script src="<s:url value="/js/chart-js/Chart.js"/>"></script>
<script type="text/javascript">
(function(){
    var t;
    function size(animate){
        if (animate == undefined){
            animate = false;
        }
        clearTimeout(t);
        t = setTimeout(function(){
            $("canvas").each(function(i,el){
                $(el).attr({
                    "width":$(el).parent().width(),
                    "height":$(el).parent().outerHeight()
                });
            });
            redraw(animate);
            var m = 0;
            $(".chartJS").height("");
            $(".chartJS").each(function(i,el){ m = Math.max(m,$(el).height()); });
            $(".chartJS").height(m);
        }, 30);
    }
    $(window).on('resize', function(){ size(false); });


    function redraw(animation){
        var options = {};
        if (!animation){
            options.animation = false;
        } else {
            options.animation = true;
        }


        var barChartData = {
            labels : [<s:property value="calendarMonth"/>],
            datasets : [
                {
                    fillColor : "#E67A77",
                    strokeColor : "#E67A77",
                    data : [<s:property value="calendarValue1"/>]
                },
                {
                    fillColor : "#79D1CF",
                    strokeColor : "#79D1CF",
                    data : [<s:property value="calendarValue2"/>]
                }
            ]

        }

        var myLine = new Chart(document.getElementById("bar-chart-js").getContext("2d")).Bar(barChartData);


    }




    size(true);

}());
</script>


<!--common script init for all pages-->
<script src="<s:url value="/js/scripts.js"/>"></script>

<script type="text/javascript">
var Script = function () {


    /* initialize the external events
     -----------------------------------------------------------------*/

    $('#external-events div.external-event').each(function() {

        // create an Event Object (http://arshaw.com/fullcalendar/docs/event_data/Event_Object/)
        // it doesn't need to have a start or end
        var eventObject = {
            title: $.trim($(this).text()) // use the element's text as the event title
        };

        // store the Event Object in the DOM element so we can get to it later
        $(this).data('eventObject', eventObject);

        // make the event draggable using jQuery UI
        $(this).draggable({
            zIndex: 999,
            revert: true,      // will cause the event to go back to its
            revertDuration: 0  //  original position after the drag
        });

    });


    /* initialize the calendar
     -----------------------------------------------------------------*/

    var date = new Date();
    var d = date.getDate();
    var m = date.getMonth();
    var y = date.getFullYear();

    $('#calendar').fullCalendar({
        header: {
            left: 'prev,next today',
            center: 'title',
            right: 'month'
        },
        editable: true,
        droppable: true, // this allows things to be dropped onto the calendar !!!
        drop: function(date, allDay) { // this function is called when something is dropped

            // retrieve the dropped element's stored Event Object
            var originalEventObject = $(this).data('eventObject');

            // we need to copy it, so that multiple events don't have a reference to the same object
            var copiedEventObject = $.extend({}, originalEventObject);

            // assign it the date that was reported
            copiedEventObject.start = date;
            copiedEventObject.allDay = allDay;

            // render the event on the calendar
            // the last `true` argument determines if the event "sticks" (http://arshaw.com/fullcalendar/docs/event_rendering/renderEvent/)
            $('#calendar').fullCalendar('renderEvent', copiedEventObject, true);

            // is the "remove after drop" checkbox checked?
            if ($('#drop-remove').is(':checked')) {
                // if so, remove the element from the "Draggable Events" list
                $(this).remove();
            }

        },
        events: [
	
           	<s:iterator value="workOffDayCalendars">
            {
                title: '<s:property value="event"/>',
                start: new Date(<s:property value="year"/>, <s:property value="month"/>, <s:property value="day"/>)
            },
            </s:iterator>
            

        ]
    });


}();
</script>


</body>
</html>

