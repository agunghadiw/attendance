function page(start,count) {
	document.forms[0].gotoPage.value = '';
	document.forms[0].start.value = start;
	document.forms[0].count.value = count;
	document.forms[0].submit();
};

function gotoPager() {
	if (document.forms[0].gotoPage.value >= 1) {
		if (document.forms[0].count.value == '' || document.forms[0].count.value == 0) document.forms[0].count.value = 10; 
		var a = (document.forms[0].gotoPage.value - 1) * document.forms[0].count.value;
		document.forms[0].start.value = a;
		document.forms[0].gotoPage2.value = document.forms[0].gotoPage.value;
		document.forms[0].submit();
	}
};

function gotoPager2() {
	if (document.forms[0].gotoPage2.value >= 1) {
		if (document.forms[0].count.value == '' || document.forms[0].count.value == 0) document.forms[0].count.value = 10; 
		var a = (document.forms[0].gotoPage2.value - 1) * document.forms[0].count.value;
		document.forms[0].start.value = a;
		document.forms[0].gotoPage.value = document.forms[0].gotoPage2.value;
		document.forms[0].submit();
	}
};

function sort(orderBy) {
	document.forms[0].orderBy.value = orderBy;
	if (document.forms[0].ascDesc.value=='') {
		document.forms[0].ascDesc.value='desc';
	} else if (document.forms[0].ascDesc.value=='desc') {
		document.forms[0].ascDesc.value='asc';
	} else {
		document.forms[0].ascDesc.value='desc';
	}
	document.forms[0].submit();
};

//to be placed in your layout/template so that it's applied to all pages
$(document).ready(function() {
  jQuery.validator.setDefaults({
    errorPlacement: function(error, element) {
      // if the input has a prepend or append element, put the validation msg after the parent div
      if(element.parent().hasClass('input-prepend') || element.parent().hasClass('input-append')) {
        error.insertAfter(element.parent());		
      // else just place the validation message immediatly after the input
      } else {
        error.insertAfter(element);
      }
    },
    errorElement: "small", // contain the error msg in a small tag
    wrapper: "div", // wrap the error message and small tag in a div
    highlight: function(element) {
      $(element).closest('.control-group').addClass('error'); // add the Bootstrap error class to the control group
    },
    success: function(element) {
      $(element).closest('.control-group').removeClass('error'); // remove the Boostrap error class from the control group
    }
  });
});




