 
$(document).ready(function() {
 
 
   $.extend({
      getUrlVars : function(){
         var vars = [], hash;
         var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
         for(var i = 0; i < hashes.length; i ++ )
         {
            hash = hashes[i].split('=');
            vars.push(hash[0]);
            vars[hash[0]] = hash[1];
         }
         return vars;
      }
      ,
      getUrlVar : function(name){
         return $.getUrlVars()[name];
      }
   }
   );
 
 
   var byName = $.getUrlVar('id');

   row_name = '#row_' + byName;

  
   $('.details').hide();
   $(row_name).show();
   var a = $(row_name).closest('tr').prev().find('td:first a').toggleClass('blueexpand');

   $('a.bluecollapse').click( function() {

  
      $(this).toggleClass('blueexpand');

      $(this).parent().parent().next(".details").toggle();
      $(this).parent().parent().toggleClass('noborder');
   }
   );
   
   $("#activitytable").tablesorter({headers : {2: {sorter : false}}}).tablesorterPager({container: $("#pager"), size: 5, positionFixed: false});

}
);
