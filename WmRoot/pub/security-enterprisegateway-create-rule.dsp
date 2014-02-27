<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 3.2//EN">
<html>
<head>
  <meta http-equiv="Pragma" content="no-cache">
  <meta http-equiv="'Content-Type'" content="'text/html;" charset="UTF-8'">
  <meta http-equiv="Expires" content="-1">
  <style>
	.deleteButton {
		background:url('icons/IS_Delete.png') no-repeat;
		width: 16px;
		height: 16px;
		cursor:hand;
		cursor:pointer;
		border: none;
	}
	
	.deleteDisableButton {
		background:url('icons/IS_Delete_disabled.png') no-repeat;
		width: 16px;
		height: 16px;
		border: none;
	}
	
	.addButton {
		background:url('icons/IS_Add.png') no-repeat;
		width: 16px;
		height: 16px;
		cursor:hand;
		cursor:pointer;
        border: none;
	}
  </style>
  <link rel="stylesheet" type="text/css" href="webMethods.css">
  <script src="webMethods.js.txt"></script>

  <script>
  
  	  var allDeviceTypes = new Array();
	  var globalDeviceTypeIndex = 0;
	  
	  var allMobileApps = new Array();
	  var globalallMobileAppsIndex = 0;
	  var disabledButton = null;
	  var disabledButtonID = null;
	function validate(currentForm)
	{
		
		currentForm.ruleName.value = trim(currentForm.ruleName.value);
		if(isEmpty(currentForm.ruleName.value))
		{
			alert("Please enter a valid Rule Name");
			currentForm.ruleName.focus();
			return false;
		}
		
		if(currentForm.enableRule.checked) {
		currentForm.isRuleEnabled.value = "true";
		} else {
		currentForm.isRuleEnabled.value = "false";
		}
		
		if(currentForm.mobileApplicationProtection.checked) {
		currentForm.isMobileAppProtectionEnabled.value = "true";
		} else {
		currentForm.isMobileAppProtectionEnabled.value = "false";
		}
		//
		if(currentForm.oAuth.checked) {
		currentForm.isOAuthEnabled.value = "true";
		currentForm.isRequireOAuthCredentials.value = "true";
		} else {
		currentForm.isOAuthEnabled.value = "false";
		currentForm.isRequireOAuthCredentials.value = "false";
		}
				
		if(currentForm.messageSize.checked) {
			currentForm.isMessageSizeEnabled.value = "true";
		
			var maximumMessageSize = currentForm.maximumMessageSize.value;
			if( (isEmpty (maximumMessageSize) || isNaN(maximumMessageSize)) || parseInt(maximumMessageSize) < 1 || !isInteger(maximumMessageSize))
			{
				alert("Please enter valid number greater than 0 for Maximum Message size");
				currentForm.maximumMessageSize.focus();
				return false;
			}
		} else {
			currentForm.isMessageSizeEnabled.value = "false";
		}
		var tableObj = document.getElementById('conditionTable');
		var rowCount = tableObj.rows.length;
		//alert(rowCount);
		if(currentForm.mobileApplicationProtection.checked) {
			if(!validateMobAppData()) {
				return false;
			}
		}
		currentForm.noOfProperties.value  = rowCount-1;
		document.getElementById("fromUI").value = "true";
		return true;
	}
	
	function validateMobAppData() {
		
		var node_list1 = document.getElementsByTagName('select');
		for (var i = 0; i < node_list1.length; i++) {
			var node1 = node_list1[i];
			var nm = node1.getAttribute('name');
			if(nm.indexOf("applicationName") != -1) {
				var selIndex = node1.selectedIndex;
				if(selIndex == -1) {
					alert("You must specify a value for the field : 'Mobile Application'.");
					node1.focus();
					return false;
				}
				var str = node1.options[node1.selectedIndex].text;
				if(str == null || isEmpty(str)) {
					alert("You must specify a value for the field : 'Mobile Application'.");
					node1.focus();
					return false;
				}
			}

			if(nm.indexOf("deviceType") != -1) {
				var selIndex = node1.selectedIndex;
				if(selIndex == -1) {
					alert("You must specify a value for the field : 'Device Type'.");
					node1.focus();
					return false;
				}
				var str = node1.options[node1.selectedIndex].text;
				if(str == null || isEmpty(str)) {
					alert("You must specify a value for the field : 'Device Type'.");
					node1.focus();
					return false;
				}
			}
			
		}
		
		var node_list = document.getElementsByTagName('input');
 
		for (var i = 0; i < node_list.length; i++) {
			var node = node_list[i];
		 
			if (node.getAttribute('type') == 'text') {
				var nm = node.getAttribute('name');
				if(nm.indexOf("applicationVersion") != -1) {
				
					var str = node.value; 
					var patt1= /(^(\d)+$)|(^(\d)+\.(\d)+$)|(^(\d)+\.(\d)+\.(\d)+$)|(^(\d)+\.(\d)+\.(\d)+\.(\d)+$)/g;
					if(null == str.match(patt1)) {
						alert("You must specify a valid value for field :'Mobile Application Version'.");
						node.focus();
						return false;
					}
				}
				
			}
		}
		return true;
	} 
	
	function updateRequireOAuthCredentials(currentForm)
	{
		if(currentForm.oAuth.checked) {
			currentForm.requireOAuthCredentials.checked = true;
		}
		else
		{
			currentForm.requireOAuthCredentials.checked = false;
		}
		
	}
	
	function updateOAuth(currentForm)
	{
		if(currentForm.requireOAuthCredentials.checked) {
			currentForm.oAuth.checked = true;
		}
		else
		{
			currentForm.oAuth.checked = false;
		}
		
	}
	function toggleresourcePath()
	{
			var selectOption =  document.getElementById("requestType"); 
			var resourcePath = document.getElementById("resourcePath"); 
			if(selectOption.selectedIndex!=0)
			{
				resourcePath.disabled="";		
			}
			else {
				resourcePath.disabled="disabled";
				resourcePath.value="";
			}
	}

	function toggleErrorMsg(action){
		var customErrorMessage = document.getElementById("customErrorMessage")
		if (action == 'deny')
		{
			customErrorMessage.disabled = false;
		}
		else if (action == 'alert')
		{
			customErrorMessage.disabled = true;
		}
	}
	
	function  deleteCondition(rownum) {
		var tableObj = document.getElementById('conditionTable');
		var tRows = tableObj.rows;

		if(tRows.length==2) {
			alert("Atleast one condition is required.");
			return;
		}
		for(var i=0 ; i < tRows.length ; i++) {
			if(tRows[i].id == rownum) {
				tableObj.deleteRow(i);
				break;
			}
		}
		//if(tRows.length==2) {
			disableFirstRowDeleteButton();
		//}	
	}
	function disableFirstRowDeleteButton() {
		
		var tableObj = document.getElementById('conditionTable');
		var tRows = tableObj.rows;
		if(tRows.length != 2) {
			return;
		}
		var reqDel = document.getElementById('deleteElement'+tRows[1].id);
		reqDel.setAttribute((document.all ? 'className' : 'class'), "deleteDisableButton");
		if(navigator.appName == "Microsoft Internet Explorer") {
			reqDel.onclick = function(){};
		} else {
			reqDel.setAttribute('onclick', '');
		}
		disabledButton = reqDel;
		disabledButtonID = tRows[1].id;
	}
	function addConditionRow() 
	{
		if(disabledButton != null) {
			disabledButton.setAttribute((document.all ? 'className' : 'class'), "deleteButton");
			if(navigator.appName == "Microsoft Internet Explorer") {
				disabledButton.onclick = function(){deleteCondition(disabledButtonID);};
			} else {
				disabledButton.setAttribute('onclick', 'deleteCondition(' +"'"+ disabledButtonID + "'"+')');
			}
		}
		var	styleClass = "evencol";

		var tableObj = document.getElementById('conditionTable');
		var rowCount = tableObj.rows.length;
	
		//Create a row at the end 
		var rowObj = tableObj.insertRow(-1); 
		rowObj.setAttribute((document.all ? 'className' : 'class'), styleClass);
		rowObj.setAttribute('id','row'+rowCount);
		rowObj.setAttribute('name', 'row'+rowCount);
		
		//Add Device Type column
		var deviceType = document.createElement('select');
		deviceType.setAttribute('id', 'deviceType'+rowCount);
		deviceType.setAttribute('name', 'deviceType'+rowCount);
		deviceType.setAttribute('style', 'width:100%');

		var option;
		
		for(var index = 0; index < allDeviceTypes.length; index++) {
			option = document.createElement("option");
			option.text = allDeviceTypes[index];
			option.value = allDeviceTypes[index];
			deviceType.add(option);
		}
		
		colObj = rowObj.insertCell(-1);
		colObj.setAttribute((document.all ? 'className' : 'class'), styleClass + "-l");
		colObj.appendChild(deviceType);

		//Add Application column
		var mobApp = document.createElement('select');
		mobApp.setAttribute('id','applicationName'+rowCount);
		mobApp.setAttribute('name', 'applicationName'+rowCount);
		mobApp.setAttribute('style', 'width:100%');

		var option;
		
		for(var index = 0; index < allMobileApps.length; index++) {
			option=document.createElement("option");
			option.text =  allMobileApps[index];
			option.value = allMobileApps[index];
			mobApp.add(option);
		}
		
		colObj = rowObj.insertCell(-1);
		colObj.setAttribute((document.all ? 'className' : 'class'), styleClass + "-l");
		colObj.appendChild(mobApp);
		
		//Add Condition column
		var condition = document.createElement('select');
		condition.setAttribute('id', 'condition'+rowCount);
		condition.setAttribute('name', 'condition'+rowCount);
	
		
		var option=document.createElement("option");
		
		option.text="";
		option.value="";
		//if(dCondition == "=") {
		//}
		condition.add(option);
		
		option=document.createElement("option");
		option.text="=";
		option.value="=";
		condition.add(option);


		option=document.createElement("option");
		option.text=">";
		option.value=">";
		condition.add(option);

		option=document.createElement("option");
		option.text="<";
		option.value="<";
		condition.add(option);
		
		option=document.createElement("option");
		option.text=">=";
		option.value=">=";
		condition.add(option);

		option=document.createElement("option");
		option.text="<=";
		option.value="<=";
		condition.add(option);
		
		option=document.createElement("option");
		option.text="<>";
		option.value="<>";
		condition.add(option);

		colObj = rowObj.insertCell(-1);
		colObj.setAttribute((document.all ? 'className' : 'class'), styleClass + "-l");
		colObj.appendChild(condition);
	
		//Add Version column
		var applicationVersion = document.createElement('input');
		applicationVersion.setAttribute('type', 'text');
		applicationVersion.setAttribute('id', 'applicationVersion'+rowCount);
		applicationVersion.setAttribute('name', 'applicationVersion'+rowCount);
		applicationVersion.setAttribute('style', 'width:50%');

		colObj = rowObj.insertCell(-1);
		colObj.setAttribute((document.all ? 'className' : 'class'), styleClass + "-l");
		colObj.appendChild(applicationVersion);
		
		var addElement1 = document.createElement('input');
		addElement1.setAttribute('type', 'button');
		addElement1.setAttribute((document.all ? 'className' : 'class'), "addButton");
		if(navigator.appName == "Microsoft Internet Explorer") {
			addElement1.onclick = function(){addConditionRow();};
		} else {
			addElement1.setAttribute('onclick', 'addConditionRow()');
		}

		colObj = rowObj.insertCell(-1);
		colObj.setAttribute((document.all ? 'className' : 'class'), styleClass);
		colObj.appendChild(addElement1);
		
		var rowcntID = "row"+rowCount;
    	var deleteElement1 = document.createElement('input');
		deleteElement1.setAttribute('id' ,'deleteElement'+rowcntID);
		deleteElement1.setAttribute('name', 'deleteElement'+rowcntID);
		deleteElement1.setAttribute('type', 'button');
		deleteElement1.setAttribute((document.all ? 'className' : 'class'), "deleteButton");
		if(navigator.appName == "Microsoft Internet Explorer") {
			deleteElement1.onclick = function(){deleteCondition(rowcntID);};
			
		} else {
			deleteElement1.setAttribute('onclick', 'deleteCondition(' +"'"+ rowcntID + "'"+')');
			
		}

		colObj = rowObj.insertCell(-1);
		colObj.setAttribute((document.all ? 'className' : 'class'), styleClass);
		colObj.appendChild(deleteElement1);

	}
		
	function editConditionRow(dAppName, dType, dCondition, dVersion) 
	{
		//alert("dType:"+dType+" dCondition:"+dCondition+" dVersion:"+dVersion);
		var	styleClass = "evencol";

		var tableObj = document.getElementById('conditionTable');
		var rowCount = tableObj.rows.length;
	
		//Create a row at the end 
		var rowObj = tableObj.insertRow(-1); 
		rowObj.setAttribute((document.all ? 'className' : 'class'), styleClass);
		rowObj.setAttribute('id','row'+rowCount);
		rowObj.setAttribute('name', 'row'+rowCount);
		
		//Add Device Type column
		var deviceType = document.createElement('select');
		deviceType.setAttribute('id', 'deviceType'+rowCount);
		deviceType.setAttribute('name', 'deviceType'+rowCount);
		var option;
		
		for(var index = 0; index < allDeviceTypes.length; index++) {
			option = document.createElement("option");
			option.text = allDeviceTypes[index];
			option.value = allDeviceTypes[index];
			if(dType == allDeviceTypes[index]) {
				option.selected = true;
			}
			deviceType.add(option);
		}
		
		colObj = rowObj.insertCell(-1);
		colObj.setAttribute((document.all ? 'className' : 'class'), styleClass + "-l");
		colObj.appendChild(deviceType);
		
		//Add Application column
		var mobApp = document.createElement('select');
		mobApp.setAttribute('id','applicationName'+rowCount);
		mobApp.setAttribute('name', 'applicationName'+rowCount);
		var option;
		
		for(var index = 0; index < allMobileApps.length; index++) {
			option = document.createElement("option");
			option.text = allMobileApps[index];
			option.value = allMobileApps[index];
			if(dAppName == allMobileApps[index]) {
				option.selected = true;
			}
			mobApp.add(option);
		}
		
		colObj = rowObj.insertCell(-1);
		colObj.setAttribute((document.all ? 'className' : 'class'), styleClass + "-l");

		colObj.appendChild(mobApp);
		
		//Add Condition column
		var condition = document.createElement('select');
		condition.setAttribute('id', 'condition'+rowCount);
		condition.setAttribute('name', 'condition'+rowCount);
		
		var option=document.createElement("option");
		
		option.text="";
		option.value="";
		//if(dCondition == "=") {
		option.selected = true;
		//}
		condition.add(option);
		
		option=document.createElement("option");
		option.text="=";
		option.value="=";
		if(dCondition == "=") {
			option.selected = true;
		}
		condition.add(option);


		option=document.createElement("option");
		option.text=">";
		option.value=">";
		if(dCondition == ">" || dCondition == "&gt;") {
			option.selected = true;
		}
		condition.add(option);

		option=document.createElement("option");
		option.text="<";
		option.value="<";
		if(dCondition == "<" || dCondition == "&lt;") {
			option.selected = true;
		}
		condition.add(option);
		
		option=document.createElement("option");
		option.text=">=";
		option.value=">=";
		if(dCondition == ">=" || dCondition == "&gt;=") {
			option.selected = true;
		}
		condition.add(option);

		option=document.createElement("option");
		option.text="<=";
		option.value="<=";
		if(dCondition == "<=" || dCondition == "&lt;=") {
			option.selected = true;
		}
		condition.add(option);
		
		option=document.createElement("option");
		option.text="<>";
		option.value="<>";
		if(dCondition == "<>" || dCondition == "&lt;&gt;") {
			option.selected = true;
		}
		condition.add(option);
		
		colObj = rowObj.insertCell(-1);
		colObj.setAttribute((document.all ? 'className' : 'class'), styleClass + "-l");

		colObj.appendChild(condition);
	
		//Add Version column
		var applicationVersion = document.createElement('input');
		applicationVersion.setAttribute('type', 'text');
		applicationVersion.setAttribute('id', 'applicationVersion'+rowCount);
		applicationVersion.setAttribute('name', 'applicationVersion'+rowCount);
		applicationVersion.value = dVersion;

		colObj = rowObj.insertCell(-1);
		colObj.setAttribute((document.all ? 'className' : 'class'), styleClass + "-l");

		colObj.appendChild(applicationVersion);
		
		///////////////////
		var addElement1 = document.createElement('input');
		addElement1.setAttribute('type', 'button');
		addElement1.setAttribute((document.all ? 'className' : 'class'), "addButton");
		if(navigator.appName == "Microsoft Internet Explorer") {
			addElement1.onclick = function(){addConditionRow();};
		} else {
			addElement1.setAttribute('onclick', 'addConditionRow()');
		}
		
		colObj = rowObj.insertCell(-1);
		colObj.setAttribute((document.all ? 'className' : 'class'), styleClass);
		colObj.appendChild(addElement1);

		var rowcntID = "row"+rowCount;
    	var deleteElement1 = document.createElement('input');
		deleteElement1.setAttribute('id' ,'deleteElement'+rowcntID);
		deleteElement1.setAttribute('name', 'deleteElement'+rowcntID);
		deleteElement1.setAttribute('type', 'button');
		deleteElement1.setAttribute((document.all ? 'className' : 'class'), "deleteButton");
		if(navigator.appName == "Microsoft Internet Explorer") {
			deleteElement1.onclick = function(){deleteCondition(rowcntID);};
		} else {
			deleteElement1.setAttribute('onclick', 'deleteCondition(' +"'"+ rowcntID + "'"+')');
		}

		colObj = rowObj.insertCell(-1);
		colObj.setAttribute((document.all ? 'className' : 'class'), styleClass);
		colObj.appendChild(deleteElement1);

	}	
  </script>
</head>
%ifvar operation equals('createRule')%
	<body onLoad="setNavigation('security-enterprisegateway-create-rule.dsp', '/WmRoot/doc/OnlineHelp/wwhelp.htm?context=is_help&topic=IS_Security_EnterpriseGatewayRules_CreateRuleScrn');">
%endif%
%ifvar operation equals('editRule')%
	<body onLoad="setNavigation('security-enterprisegateway-create-rule.dsp', '/WmRoot/doc/OnlineHelp/wwhelp.htm?context=is_help&topic=IS_Security_EnterpriseGatewayRules_EditRuleScrn');">
%endif%
%ifvar operation equals('copyRule')%
	<body onLoad="setNavigation('security-enterprisegateway-create-rule.dsp', '/WmRoot/doc/OnlineHelp/wwhelp.htm?context=is_help&topic=IS_Security_EnterpriseGatewayRules_CreateRuleScrn');">
%endif%

<form method="post" name="createRule" action="security-enterprisegateway-rules.dsp" id="createRule">

<table width="100%">
	<tr>
		<td class="menusection-Security" colspan="2">Security&nbsp;&gt;&nbsp;Enterprise&nbsp;Gateway&nbsp;Rules&nbsp;&gt;&nbsp;Rules&nbsp;&gt;
			%ifvar operation equals('createRule')%
				Create&nbsp;
			%endif%
			%ifvar operation equals('editRule')%
				%value rule% &gt;&nbsp;Edit&nbsp;
			%endif%
			%ifvar operation equals('copyRule')%
				%value rule%_copy &gt;&nbsp;Create&nbsp;
			%endif%
			</td>
		</tr>
		
		%invoke wm.server.enterprisegateway:listMobileDeviceTypes%
			<script>globalDeviceTypeIndex=0;
				allDeviceTypes[globalDeviceTypeIndex] = '';
				globalDeviceTypeIndex = globalDeviceTypeIndex+1;
			</script>
			
			%loop mobileAppDeviceTypes%
				<script>
					allDeviceTypes[globalDeviceTypeIndex] = '%value%';
					globalDeviceTypeIndex = globalDeviceTypeIndex+1;
				</script>
			%endloop%
			%rename mobileAppDeviceTypes oldMobileAppDeviceTypes%
			<script>globalDeviceTypeIndex=0;</script>
		%endinvoke%
		%invoke wm.server.enterprisegateway:listMobileApplications%
			<script>
				globalallMobileAppsIndex=0;
				allMobileApps[globalallMobileAppsIndex] = '';
				globalallMobileAppsIndex = globalallMobileAppsIndex+1;
			</script>
			%loop mobileApplications%
				<script>
					allMobileApps[globalallMobileAppsIndex] = '%value%';
					globalallMobileAppsIndex = globalallMobileAppsIndex+1;
				</script>
			%endloop%
			%rename mobileApplications oldMobileApplications%
			<script>globalallMobileAppsIndex=0;</script>
		%endinvoke%
			
	<tr>
		<td colspan="2">
			<ul>
				<li><a href="security-enterprisegateway-rules.dsp">Return&nbsp;to&nbsp;Enterprise&nbsp;Gateway&nbsp;Rules</a></li>
			</ul>
		</td>
	</tr>
	<input type="hidden" name="operation" id="operation" value="%value operation%">
	<input type="hidden" name="ruleType" id="ruleType" value="%value ruleType%">
	<input type="hidden" name="rule" id="rule" value="%value rule%">
	<input type="hidden" name="index" id="index" value="%value index%">		
	%ifvar operation equals('editRule')%
				
		%invoke wm.server.enterprisegateway:getRuleByName%
			%ifvar message%
				<tr><td colspan="2">&nbsp;</td></tr>
				<tr><td class="message" colspan="2">%value message%</td></tr>
			%endif%
			%onerror%
				<tr><td colspan="2">&nbsp;</td></tr>
				<tr><td class="message" colspan=2>%value errorMessage%</td></tr>
		%endinvoke%
	%endif%
	
	%ifvar operation equals('copyRule')%
				
		%invoke wm.server.enterprisegateway:getRuleByName%
			%ifvar message%
				<tr><td colspan="2">&nbsp;</td></tr>
				<tr><td class="message" colspan="2">%value message%</td></tr>
			%endif%
			%onerror%
				<tr><td colspan="2">&nbsp;</td></tr>
				<tr><td class="message" colspan=2>%value errorMessage%</td></tr>
		%endinvoke%
	%endif%
	
	<input type="hidden" name="isRuleEnabled" id="isRuleEnabled" value="%value isRuleEnabled%">
	
	<input type="hidden" name="isOAuthEnabled" id="isOAuthEnabled" value="%value isOAuthEnabled%">
	<input type="hidden" name="isRequireOAuthCredentials" id="isRequireOAuthCredentials" value="%value isRequireOAuthCredentials%">
	<input type="hidden" name="isMessageSizeEnabled" id="isMessageSizeEnabled" value="%value isMessageSizeEnabled%">
	<input type="hidden" name="isMobileAppProtectionEnabled" id="isMobileAppProtectionEnabled" value="%value isMobileAppProtectionEnabled%">
	<input type="hidden" name="noOfProperties" id="noOfProperties">	
	<input type="hidden" name="fromUI" id="fromUI" value="false">	
	<table class="tableView" width="100%">
		<tr>
			<td width="10px">
				<img src="images/blank.gif"  border=0>
			</td>
			<td>	
				<table class="tableView" width="70%">
					<tr>
						<td class="heading" colspan="2" width="100%"> Rule Properties</td>
					</tr>
					<tr>
						<td class="oddrow" width="20%">Rule Name</td>
						<td class="oddrow-l" width="80%">
						
							%ifvar operation equals('createRule')%
								<input type="text" name="ruleName" id="ruleName" form="createRule" size="57" >
							%else%
								<input type="text" name="ruleName" id="ruleName" %ifvar operation equals('editRule')% value="%value ruleName%" readonly="readonly"%else%  value="%value ruleName%_copy" %endif% form="createRule" size="57">
							%endif%
						</td>
		
					</tr>
					<tr >	
						<td class="evenrow" width="20%">Description</td>
						<td class="evenrow-l" width="80%">
							<textarea name="ruleDescription" rows="4" cols="54" form="createRule">%value ruleDescription%</textarea>
						</td>
					</tr>
					<tr >
						<td class="oddrow" width="20%">&nbsp;&nbsp; Enable</td>
						<td class="oddrow-l" width="80%">&nbsp;<input type="checkbox" name="enableRule" id="enableRule" value="Enable"   %ifvar isRuleEnabled equals('true')%checked%endif%></td></tr>
					<tr>
						<td class="evenrow" width="20%"> 
							 Request Type
						</td>
						<td class="evenrow-l" width="80%">
							<select size="1" name="requestType" id="requestType" onchange="toggleresourcePath()">
								%ifvar operation equals('createRule')%
								<option selected value="ALL">ALL</option>
								%else%
								<option %ifvar requestType equals('ALL')%selected%endif% value="ALL">ALL</option>
								 %endif%
								<option %ifvar requestType equals('SOAP')%selected%endif% value="SOAP">SOAP</option> 
								<option %ifvar requestType equals('REST')%selected%endif% value="REST">REST</option>
								<option %ifvar requestType equals('INVOKE')%selected%endif% value="INVOKE">INVOKE</option> 
							</select>
						</td>
					</tr>
					<tr>
						<td class="oddrow" width="20%">Resource Path
						</td>
						<td class="oddrow-l" width="80%">		
							%ifvar operation equals('createRule')%
								<textarea name="resourcePath" id="resourcePath"  rows="4" cols="54" disabled="disabled"></textarea>
							%else%
								%ifvar requestType equals('ALL')%
									<textarea name="resourcePath" id="resourcePath"  rows="4" cols="54" disabled="disabled"></textarea>
								%else%
									<textarea name="resourcePath" id="resourcePath"  rows="4" cols="54">%value resourcePath%</textarea>
								%endif%
							%endif%
							(ex:folder_name/service_name)
						</td>	
					</tr>
					<tr>		  
						<td class="oddrow" width="20%"/>
						<td class="oddrow-l" width="80%" colspan=2>Enter one resource path per line</TD>						
					</tr>
					<tr >
						<td class="evenrow" width="20%">Action</td>
						<td class="evenrow-l" width="80%">
							<table>
							<tr>
							  <td>
								
								%ifvar operation equals('createRule')%
									<input type="radio" name="action" id="deny" value="DENY"   checked onclick="toggleErrorMsg('deny');">&nbsp; Deny Request and Alert
								%else%
									<input type="radio" name="action" id="deny" value="DENY"  %ifvar action equals('DENY')%checked%endif% onclick="toggleErrorMsg('deny');">&nbsp; Deny Request and Alert
								%endif%
								
							  </td>
							   <td>
								 <input type="radio" name="action" id="alert" value="ALERT"  %ifvar action equals('ALERT')%checked%endif% onclick="toggleErrorMsg('alert');">&nbsp;&nbsp;Alert
							   </td>
							</tr>
							
						  </table>
					 </td>
					</tr>
					<tr>
							<td class="oddrow" width="20%">Error Message</td>
							<td class="oddrow-l" width="80%">
								%ifvar operation equals('createRule')%
									<input type="text" name="customErrorMessage" id="customErrorMessage"  value="Access Denied" size="57" >
								%else%
									<input type="text" name="customErrorMessage" id="customErrorMessage"  value="%value customErrorMessage%" %ifvar action equals('ALERT')% disabled%endif% size="57" >
								%endif%
							</td>
					</tr>
				 </table>
				 <br>
 <!-- Filter Table Starts -->
 <table class="tableView" width="100%">
		<tr>
		    <table class="tableView" width="70%" id="filterTable">
				<TBODY>
					<tr>
						<td class="heading" colspan="2" width="100%">OAuth Filter</td>
					</tr>
					<tr>
						<td  width="20%" class="oddrow">Enable</td>
						<td  width="80%" class="oddrow-l">
							<input type="checkbox" name="oAuth" id="oAuth"  onclick="updateRequireOAuthCredentials(this.form)" %ifvar isOAuthEnabled equals('true')%checked%endif%>
						</td>
					</tr>
					<tr>
						<td  width="20%" class="evenrow">Require OAuth Credentials</td>
						<td  width="70%" class="evenrow-l">
							<input type="checkbox"  name="requireOAuthCredentials" id="requireOAuthCredentials" onclick="updateOAuth(this.form)" %ifvar isRequireOAuthCredentials equals('true')%checked%endif%>
						</td>
					</tr>
					<tr>
						<td class="heading" colspan="2" width="100%">Message Size Filter</td>
					</tr>	
					<tr>
						<td  width="20%" class="oddrow">Enable</td>
						<td  width="80%" class="oddrow-l">
							<input type="checkbox" name="messageSize" id="messageSize"  %ifvar isMessageSizeEnabled equals('true')%checked%endif%>
						</td>
					</tr>
					<tr>				
						<td width="20%" class="evenrow">Maximum Message Size</td>
						<td width="80%" class="evenrow-l">
							%ifvar operation equals('createRule')%
								<input name="maximumMessageSize" id="maximumMessageSize" >&nbsp;MB
							%else%
								<input name="maximumMessageSize" id="maximumMessageSize"  value="%value maximumMessageSize%">&nbsp;MB
							%endif%
						</td>
					</tr>
					<tr>
						<td class="heading" colspan="2" width="100%">Mobile Application Protection Filter</td>
					</tr>	
					<tr>
						<td width="20%" class="oddrow">Enable</td>
						<td  width="80%" class="oddrow-l">
							<input type="checkbox" name="mobileApplicationProtection" id="mobileApplicationProtection"  %ifvar isMobileAppProtectionEnabled equals('true')%checked%endif%>
						</td>
						</tr>
					<tr>	
						<td width="20%" class="evenrow">Conditions</td>
						<td  width="80%" class="oddrow-l">
						  	<table width="100%">
								<tr>
									<td>
										<table id="conditionTable" name="conditionTable" class="table" width="100%">
											<tr>
												<td class="evencol-l">Device Type</td>
												<td class="evencol-l">Mobile Application</td>
												<td class="evencol-l">Condition</td>
												<td class="evencol-l">Mobile Application Version</td>
												<td class="evencol">Add</td>
												<td class="evencol">Delete</td>
											</tr>
											%ifvar operation equals('createRule')%
												<script>addConditionRow();disableFirstRowDeleteButton();</script>
											%endif%	
										</table>
									</td>
									%ifvar operation equals('createRule')%
									%else%
										 %ifvar -notempty mobileApplications%
											%loop mobileApplications%
												<script>editConditionRow('%value applicationName%', '%value deviceType%', '%value condition%','%value applicationVersion%');</script>
											%endloop%
											<script>disableFirstRowDeleteButton();</script>
										%else%	
											<script>addConditionRow();disableFirstRowDeleteButton();</script>
										%endif%
									%endif%
								</tr>
							</table>
						</td>
					</tr>
					<tr class="action">
							<td  colspan="2" width="100%">
								<input type="submit" name="submit" value="Save Changes" onclick="return validate(this.form);">
							</td>
					</tr>
				</TBODY>
			</table>
		</tr>
 </table>
 <!-- Filter Table ends -->

 </p></table>
 </form>
</body>
</html>