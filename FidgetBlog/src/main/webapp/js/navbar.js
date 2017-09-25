


$(document).ready(function(){
    listStaticPages();
});

var ctx = $('ctx');

function listStaticPages(){
    
    $('#staticpage-list').empty();
    
    $.ajax({
        
        url: 'http://localhost:8080/FidgetBlog/getstaticpages',
        contentType: "application/json",
        dataType: "json",
        
        
        success: function(staticPageArray){
            
            $.each(staticPageArray, function(index, staticPage){
                
                
                var id = staticPage.staticPageId;
                var title = staticPage.staticPageTitle;
                
                
                                
                console.log(staticPage.staticPageId);
                console.log(staticPage.staticPageTitle);
                
                
                var staticPageList = $('#staticpage-list');
                
                
                var row = '<li id=\"staticpage-' + id + '\" >' + '<a href = \"' + ctx + '/static/' + id + '\">' + title + '</a></li>';
         
                staticPageList.append(row);
                
                console.log(title);
                
            });
            
       }
    });
}



