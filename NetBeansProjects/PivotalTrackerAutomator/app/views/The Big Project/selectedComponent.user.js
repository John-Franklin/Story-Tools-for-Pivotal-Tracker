/* 
 *  Copyright 2013 John Franklin.
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>
 */
// ==UserScript==
// @name         Pivotal Tracker SelectionReader
// @description  Determines which stories are selected in the current version of tracker,
// along with allowing to split those stories and their tasks into epics and stories
// @version      0.2
// @match        https://www.pivotaltracker.com/s/projects/*
// @js           jquery.js
// ==/UserScript==

//require("pivotal");

//module.exports = pivotal;
var userToken;
/*chrome.extension.sendRequest({method: "getLocalStorage", key: "pivotal_token"}, function(response) {
    if(response.data != null)
        userToken = response.data;
    else
    {
        var current = confirm("Add your token to Pivotal Tracker+'s options!\n(click OK to start the tutorial)");
        
        
    }

});*/
//var q = $("<html></html>");
//q.load("https://www.pivotaltracker.com/profile #api .api div:nth-child(2)");
if(localStorage["refreshed"] == null)
{
    localStorage["refreshed"] = false;
}
var tokenIsLoadable = true;
getToken = function()
{
    $.get("https://www.pivotaltracker.com/profile", function(data) {
         //console.log(data);
         var v = data.indexOf("<h4>API token</h4>");
         if(v == -1)
         {
             if(!localStorage["refreshed"])//Prevents endless refreshing even if this method doesn't always work.
             {
                 localStorage["refreshed"] = true;
                 location.reload(true);
             }
             else if(tokenIsLoadable)
             {
                 var q = confirm("For this app to work, you need to add an API token to Pivotal Tracker. Clicking OK will open a page where you can do this; just scroll to the bottom and click the bottom button in the API token section.");
                 if(q)
                 {
                     window.open("https://www.pivotaltracker.com/profile");
                 }
                 tokenIsLoadable = false;
             }
             return;
         }//console.log(v)
         else
             tokenIsLoadable = true;
         //console.log(data.indexOf("</div>", v))
         userToken = $.trim(data.substring(v + 40, data.indexOf("</div>", v) - 1));
         localStorage["Tracker Token"] = userToken;
         localStorage["refreshed"] = false;//allows for refresh if code completes so extension can update.
    });
}
//getToken();

if(localStorage["Tracker Token"] == null)
{
     getToken();
}
else
{
    userToken = localStorage["Tracker Token"];
}

//console.log(q);
//console.log($.get());


var projecturl = window.location.href;
var projectid = projecturl.substring(projecturl.lastIndexOf('/', projecturl.length) + 1, projecturl.length);
////alert(projectid);
var tokenid = "TODO";
//Implement
var cdown = false;
var vdown = false;
var ctrldown = false;
var appldown = false;
var storyidlist = [];
var clicked = false;
var isinuse = false;
//prompt("blah");
$(document).ready(function(){
    ////alert("HERE")
        
    var link1 = document.createElement("li");
    var link2 = document.createElement("li");
    
    link1.innerHTML = "<span class = \"disabled\">Make Stories into Epics</span>";
    link2.innerHTML = "<span class = \"disabled\">Duplicate</span>";
    link1.className = "item split_story";
    link2.className = "item duplicate";
    link1.id = "split_story";
    link2.id = "duplicate";
    var r = function()
            {
                    ////alert("")
                    if($(".count").length > 0)
                    {
                        $("#split_story > span").removeClass("disabled")
                        $("#duplicate > span").removeClass("disabled")
                    }
                    else
                    {
                        $("#split_story > span").addClass("disabled")
                        $("#duplicate > span").addClass("disabled")
                    }
                        
                    if($("#split_story").length == 0)
                    {
                        $("#panels_control > div > section > div.stories > ol").append(link1);
                        $("#panels_control > div > section > div.stories > ol").append(link2);
                        
                        if($("#split_story").length > 0 && !clicked)//successsfully added,
                        {
                            //alert("In window function?")
                            //$(".stories").click(q)
                            //$("#tracker").unbind("click", q)
                            $(".split_story").click(function(){
                                ////alert("...");
                                //alert("In button function1?")
                                var v = getSelectedStoryIDs();
                                for(var i = 0; i < v.length; i++)
                                {
                                    splitStorytoEpic(userToken, v[i], projectid)
                                }

                            })
                            $(".duplicate").click(function(){
                                ////alert("...");
                                //alert("In button function2?")
                                var v = getSelectedStoryIDs();
                                for(var i = 0; i < v.length; i++)
                                {
                                    duplicatev5(userToken, v[i], projectid)
                                }
                            });
                            clicked = true;
                        }
                    }

            }
    $("#tracker").ready(function(){
        $("#tracker").click(r);
    });
});
    
//document.getElementsbyClassName("stories")[0].getElementsbyClassName("items")[0].add("<li class=\"item\"><a href=\"#\" class=\"split_story\">Split Stories into Tasks</a></li>");
//document.getElementsbyClassName("stories")[0].getElementsbyClassName("items")[0].add("<li class=\"item\"><a href=\"#\" class=\"duplicate\">Duplicates</a></li>");
/*
 * Exactly what is says on the tin.
 * Dependent on Pivotal not changing their class labelling system. 
 * However, if they made changes often, this software wouldn't be necessary.
 */
getSelectedStoryIDs = function()
{
    var finalarray = [];
    var indexdelta = 0;
    var selectedtabs = $('.current .selector.selected,.icebox .selector.selected,.backlog .selector.selected,.epic_stories .selector.selected,.done .selector.selected,.my_work .selector.selected').parent().parent();
    selectedtabs.each(function(i){
        var q = $(this).attr("class").toString();
        var id = q.substring(q.indexOf("_") + 1, q.indexOf(" "))
        var idispresent = false;
        for(var j = 0; j < finalarray.length; j++)
        {
            //not expected to be more than 10-15, usually 1 - 5, so O(n^2) isn't a problem here.
            if(finalarray[j] == id)
            {
                idispresent = true;
                indexdelta++;
                break;
            }
        }

        //console.log(q)
        if(!idispresent)
            finalarray[i - indexdelta] = q.substring(q.indexOf("_") + 1, q.indexOf(" "));
    })
    /*selectedtabs.each(function(i,e){
        var q = e.attr('class');
        finalarray[i] = q.subString(q.indexOf("_"), q.indexOf(" "))
    })*/
    //console.log(finalarray)
    return finalarray;


}
/*
 * does as it says, but lacks attachment support. In the future it may be an option.
 * Issues: buttons selecting these functions do not work correctly with a story or epic tab open(IE clicked either new story button or new epic button or fixing an old one.)
 */
splitStorytoEpic = function(token, storyId, projectId)
{
    ////alert("...")
    var request = new XMLHttpRequest();
    //var request2 = new XMLHttpRequest();
    request.open("GET", "http://www.pivotaltracker.com/services/v3/projects/"+ projectId +
    "/stories/" + storyId, true);
    request.setRequestHeader("X-TrackerToken", token);
    request.onreadystatechange = function(){
        ////alert(request.responseText)
        if(request.readyState == 4 && request.status == 401)
        {
            getToken();
            if(tokenIsLoadable)
            {
                splitStorytoEpic(userToken, storyId, projectId);
            }
        }
        else if(request.readyState == 4)
        {
            var storyDom = $(request.responseText.substring(request.responseText.indexOf("\n") + 1));
            ////alert($(storyDom).html())
            var newXML = "<story><labels>"
            var epicname = $(storyDom).get(0).getElementsByTagName("name")[0].innerHTML
            newXML += epicname

            if($(storyDom).get(0).getElementsByTagName("labels").length > 0)
                newXML += "," + $(storyDom).get(0).getElementsByTagName("labels")[0].innerHTML;
            newXML += "</labels>";

            if($(storyDom).get(0).getElementsByTagName("description").length > 0)
                var epicdescription = $(storyDom).get(0).getElementsByTagName("description")[0].innerHTML;
            else
                epicdescription = "";
            var taskArray = $(storyDom).get(0).getElementsByTagName("task");
            if(taskArray <= 0)
                return false;
            for(var i = 0; i < taskArray.length; i++)
            {
                var tempXML = newXML;
                tempXML += "<name>" + taskArray[i].getElementsByTagName("description")[0].innerHTML + "</name>"
                if(taskArray[i].getElementsByTagName("complete")[0].innerHTML == "false")
                   tempXML += "<current_state>unscheduled</current_state>";
                else
                {
                    tempXML += "<current_state>finished</current_state>";
                    tempXML += "<estimate>1</estimate>";//must set estimate here or you get 422(stories that have been started must have estimates)
                }
                //tempxml +=
                
                tempXML += "</story>";
                //console.log(tempXML)
                var request2 = new XMLHttpRequest();
                request2.open("post", "http://www.pivotaltracker.com/services/v3/projects/"+ projectId +
                "/stories", false);
                request2.setRequestHeader("Content-type", "application/xml; charset=utf8");
                request2.setRequestHeader("X-TrackerToken", token);
                request2.onreadystatechange = function(){
                    request2.onreadystatechange = function(){
                    //Necessary because you can have the rights to access a file without the rights to edit it, like with public projects.
                    //alert(request.responseText)
                    if(request2.readyState == 4 && request2.status == 401)
                    {
                        getToken();
                        if(tokenIsLoadable)
                        {
                            request2.send(newXML);
                        }
                    }
            }
                    ////alert(request.responseText)
                    if(request2.readyState == 4 && request2.status == 401)
                    {
                        getToken();
                        if(tokenIsLoadable)
                        {
                            request2.send(tempXML);
                        }
                    }
                }
                request2.send(tempXML);

            }
            var request3 = new XMLHttpRequest();
            request3.open("delete", "http://www.pivotaltracker.com/services/v3/projects/"+ projectId +
            "/stories/" + storyId, true);
            request3.setRequestHeader("Content-type", "application/xml; charset=utf8");
            request3.setRequestHeader("X-TrackerToken", token);
            request3.send();
            createEpic(userToken, projectId, epicname, epicdescription);
        }
        
        
        return true;
    }
    request.send();

}
/*splitStorytoEpicv5 = function(token, storyId, projectId)
{
    ////alert("...")
    var request = new XMLHttpRequest();
    //var request2 = new XMLHttpRequest();
    request.open("GET", "http://www.pivotaltracker.com/services/v5/projects/"+ projectId +
    "/stories/" + storyId, true);
    request.setRequestHeader("X-TrackerToken", token);
    request.onreadystatechange = function(){
        ////alert(request.responseText)
        if(request.readyState == 4 && request.status == 401)
        {
            getToken();
            //if(tokenIsLoadable)
            //{
                splitStorytoEpic(userToken, storyId, projectId);
            //}
        }
        else if(request.readyState == 4)
        {
            var storyDom = $(request.responseText.substring(request.responseText.indexOf("\n") + 1));
            ////alert($(storyDom).html())
            var newXML = "<story><labels>"
            var epicname = $(storyDom).get(0).getElementsByTagName("name")[0].innerHTML
            newXML += epicname

            if($(storyDom).get(0).getElementsByTagName("labels").length > 0)
                newXML += "," + $(storyDom).get(0).getElementsByTagName("labels")[0].innerHTML;
            newXML += "</labels>";

            if($(storyDom).get(0).getElementsByTagName("description").length > 0)
                var epicdescription = $(storyDom).get(0).getElementsByTagName("description")[0].innerHTML;
            else
                epicdescription = "";
            var taskArray = $(storyDom).get(0).getElementsByTagName("task");
            if(taskArray <= 0)
                return false;
            for(var i = 0; i < taskArray.length; i++)
            {
                var tempXML = newXML;
                tempXML += "<name>" + taskArray[i].getElementsByTagName("description")[0].innerHTML + "</name>"
                if(taskArray[i].getElementsByTagName("complete")[0].innerHTML == "false")
                   tempXML += "<current_state>unscheduled</current_state>";
                else
                {
                    tempXML += "<current_state>finished</current_state>";
                    tempXML += "<estimate>1</estimate>";//must set estimate here or you get 422(stories that have been started must have estimates)
                }
                //tempxml +=

                tempXML += "</story>";
                //console.log(tempXML)
                var request2 = new XMLHttpRequest();
                request2.open("post", "http://www.pivotaltracker.com/services/v3/projects/"+ projectId +
                "/stories", false);
                request2.setRequestHeader("Content-type", "application/xml; charset=utf8");
                request2.setRequestHeader("X-TrackerToken", token);
                if(i == taskArray.length - 1)
                    request2.onreadystatechange = function(){
                        if(request2.readyState == 4 && request2.status != 500 && request2.status != 401)
                        {
                            var request3 = new XMLHttpRequest();
                            request3.open("delete", "http://www.pivotaltracker.com/services/v3/projects/"+ projectId +
                            "/stories/" + storyId, true);
                            request3.setRequestHeader("Content-type", "application/xml; charset=utf8");
                            request3.setRequestHeader("X-TrackerToken", token);
                            request3.send();
                            createEpic(userToken, projectId, epicname, epicdescription);
                        }
                    }

                request2.send(tempXML);
            }
        }


        return true;
    }
    request.send();

}*/ // Commented out to avoid long loading times. scripted language.
/*
 * because javascript is only asynchronous and not multithreaded, don't need to worry about issues here.
 * if that were to change, though...
 */
createEpic = function(token, projectId, epicname, epicdescription)
{
    var epicJSON = '{"description":"'+ epicdescription +
        '","label":{"name":"'+epicname+'"},"name":"'+epicname+'"}';
    var request = new XMLHttpRequest();
    request.open("post", "https://www.pivotaltracker.com/services/v5/projects/"+ projectId +
                "/epics", true);
    request.setRequestHeader("Content-type", "application/json");
    request.setRequestHeader("X-TrackerToken", token);
    request.send(epicJSON);
}
duplicatev5 = function(token, storyId, projectId)
{
    var request = new XMLHttpRequest();
    //var request2 = new XMLHttpRequest();
    request.open("GET", "https://www.pivotaltracker.com/services/v5/projects/"+ projectId +
    "/stories/" + storyId, true);
    request.setRequestHeader("Content-type", "application/json");
    request.setRequestHeader("X-TrackerToken", token);
    request.send();
    request.onreadystatechange = function()
    {
        if(request.readyState == 4 && request.status == 401)
        {
            getToken();
            if(tokenIsLoadable)
            {
                duplicatev5(userToken, storyId, projectId);
            }
        }
        else if(request.readyState == 4)
        {
            var request3 = new XMLHttpRequest();
            //var request2 = new XMLHttpRequest();
            request3.open("GET", "https://www.pivotaltracker.com/services/v5/projects/"+ projectId +
            "/stories/" + storyId +"/tasks", true);
            request3.setRequestHeader("Content-type", "application/json");
            request3.setRequestHeader("X-TrackerToken", token);
            request3.onreadystatechange = function(){
                if(request3.readyState == 4)
                {
                    console.log(request.responseText);
                    console.log(request3.responseText);
                    var storycopy = eval("(" + request.responseText + ")");
                    delete storycopy.id;
                    delete storycopy.owned_by_id;
                    //delete storycopy.owner_ids;
                    storycopy.tasks = eval("(" + request3.responseText + ")");
                    for(var i = 0; i < storycopy.tasks.length; i++)
                    {
                        delete storycopy.tasks[i].story_id;
                        delete storycopy.tasks[i].id;
                        delete storycopy.tasks[i].kind;
                        delete storycopy.tasks[i].created_at;
                        delete storycopy.tasks[i].updated_at;
                    }
                    //storycopy.owner_ids = [];
                    
                    var request2 = new XMLHttpRequest();

                    //var request2 = new XMLHttpRequest();
                    request2.open("post", "https://www.pivotaltracker.com/services/v5/projects/"+ projectId +
                    "/stories", true);
                    request2.setRequestHeader("Content-type", "application/json");
                    request2.setRequestHeader("X-TrackerToken", token);
                    request2.onreadystatechange = function()
                    {
                        if(request2.readyState == 4 && request2.status == 401)
                        {
                            getToken();
                            if(tokenIsLoadable)
                            {
                                request2.send(JSON.stringify(storycopy));
                            }
                        }
                    }
                    console.log(JSON.stringify(storycopy));
                    request2.send(JSON.stringify(storycopy));
                }
            }
            request3.send();
        }
    }

} // See above.
/*duplicate = function(token, storyId, projectId)
{
    //alert("multiple?")
    var request = new XMLHttpRequest();
    //var request2 = new XMLHttpRequest();
    request.open("GET", "http://www.pivotaltracker.com/services/v3/projects/"+ projectId +
    "/stories/" + storyId + "?token=" +token, true);
    request.onreadystatechange = function(){
        if(request.readyState == 4 && request.status == 401)
        {
            getToken();
            if(tokenIsLoadable)
            {
                duplicate(userToken, storyId, projectId);
            }
        }
        else if(request.readyState == 4)
        {

            //console.log(request.responseText);
            //alert("how many times does ready state change?")
            var storyDom = $(request.responseText.substring(request.responseText.indexOf("\n") + 1));
            ////alert($(storyDom).html())
            var newXML = "<story>"
            if($(storyDom).get(0).getElementsByTagName("labels").length > 0)
                newXML += $(storyDom).get(0).getElementsByTagName("labels")[0].outerHTML;
            if($(storyDom).get(0).getElementsByTagName("name").length > 0)
                newXML += $(storyDom).get(0).getElementsByTagName("name")[0].outerHTML;
            if($(storyDom).get(0).getElementsByTagName("description").length > 0)
                newXML += $(storyDom).get(0).getElementsByTagName("description")[0].outerHTML;
            if($(storyDom).get(0).getElementsByTagName("current_state").length > 0)
                newXML += $(storyDom).get(0).getElementsByTagName("current_state")[0].outerHTML;
            if($(storyDom).get(0).getElementsByTagName("estimate_type").length > 0)
                newXML += $(storyDom).get(0).getElementsByTagName("estimate_type")[0].outerHTML;
            if($(storyDom).get(0).getElementsByTagName("estimate").length > 0)
                newXML += $(storyDom).get(0).getElementsByTagName("estimate")[0].outerHTML;
            if($(storyDom).get(0).getElementsByTagName("story_type").length > 0)
                newXML += $(storyDom).get(0).getElementsByTagName("story_type")[0].outerHTML;


            newXML += "</story>";
            ////alert
            var request2 = new XMLHttpRequest();
            request2.open("post", "http://www.pivotaltracker.com/services/v3/projects/"+ projectId +
            "/stories", false);//must be false. causes serious errors without it and won't be able to get id to move tasks.
            request2.onreadystatechange = function(){
                    //Necessary because you can have the rights to access a file without the rights to edit it as with public projects.
                    //alert(request.responseText)
                    if(request2.readyState == 4 && request2.status == 401)
                    {
                        getToken();
                        if(tokenIsLoadable)
                        {
                            request2.send(newXML);
                        }
                    }
            }
            request2.setRequestHeader("Content-type", "application/xml; charset=utf8");
            request2.setRequestHeader("X-TrackerToken", token);

            request2.send(newXML);
            //console.log("...req2" + request2.responseText);
            var storyDom2 = $(request2.responseText.substring(request2.responseText.indexOf("\n") + 1));
            var id = $(storyDom2).get(0).getElementsByTagName("id")[0].innerHTML;

            var taskArray = $(storyDom).get(0).getElementsByTagName("task");
            for(var i = 0; i < taskArray.length; i++)
            {

                var taskXML = "<task>"
                if(taskArray[i].getElementsByTagName("complete")[0] != null)
                    taskXML += taskArray[i].getElementsByTagName("complete")[0].outerHTML;
                if(taskArray[i].getElementsByTagName("description").length > 0)
                    taskXML += taskArray[i].getElementsByTagName("description")[0].outerHTML;
                taskXML += "</task>"
                //var taskXML = "<task>"
                var request3 = new XMLHttpRequest();
                request3.open("post", "http://www.pivotaltracker.com/services/v3/projects/"+ projectId +
                "/stories/"+ id + "/tasks", true)
                request3.setRequestHeader("Content-type", "application/xml; charset=utf8");
                request3.setRequestHeader("X-TrackerToken", token);
                request3.send(taskXML);
            }
            //console.log($(storyDom).get(0).getElementsByTagName("task").innerHTML);
        }
    }
    request.send();
    

}*/
