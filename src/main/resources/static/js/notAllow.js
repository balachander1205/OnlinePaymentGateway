$(document).bind("contextmenu",function(e) {
 e.preventDefault();
});
$(document).keydown(function(e){
    if(e.ctrlKey && (e.which === 83)){
       e.preventDefault();
       return false;
    }
    if(event.keyCode == 123) { return false; } 
	if(e.ctrlKey && e.keyCode == 'E'.charCodeAt(0)){ return false;} 
	if(e.ctrlKey && e.shiftKey && e.keyCode == 'I'.charCodeAt(0)){ return false; } 
	if(e.ctrlKey && e.shiftKey && e.keyCode == 'J'.charCodeAt(0)){return false; } 
	if(e.ctrlKey && e.keyCode == 'U'.charCodeAt(0)){ return false; } 
	if(e.ctrlKey && e.keyCode == 'S'.charCodeAt(0)){ return false;} 
	if(e.ctrlKey && e.keyCode == 'H'.charCodeAt(0)){ return false; }
	if(e.ctrlKey && e.keyCode == 'A'.charCodeAt(0)){ return false; }
	if(e.ctrlKey && e.keyCode == 'F'.charCodeAt(0)){ return false; }
	if(e.ctrlKey && e.keyCode == 'E'.charCodeAt(0)){ return false; }
});