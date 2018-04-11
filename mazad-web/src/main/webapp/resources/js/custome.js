/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    $("#showP").click(function () {
        
        if($("#form").hasClass("hidden")){
                $("#form").removeClass('hidden');
            }else{
                $("#form").addClass('hidden');
            }
    });
});
