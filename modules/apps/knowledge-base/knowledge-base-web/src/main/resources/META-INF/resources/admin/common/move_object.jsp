<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
--%>

<%@ include file="/admin/common/init.jsp" %>

<%
int status = (Integer)request.getAttribute(KBWebKeys.KNOWLEDGE_BASE_STATUS);

long kbArticleClassNameId = PortalUtil.getClassNameId(KBArticleConstants.getClassName());

resourceClassNameId = ParamUtil.getLong(request, "resourceClassNameId");
resourcePrimKey = ParamUtil.getLong(request, "resourcePrimKey");
long parentResourceClassNameId = ParamUtil.getLong(request, "parentResourceClassNameId");
long parentResourcePrimKey = ParamUtil.getLong(request, "parentResourcePrimKey");

String title = null;
String parentTitle = null;
double priority = KBArticleConstants.DEFAULT_PRIORITY;
int targetStatus = status;

if (resourceClassNameId == kbArticleClassNameId) {
	KBArticle kbArticle = KBArticleServiceUtil.fetchLatestKBArticle(resourcePrimKey, status);

	title = kbArticle.getTitle();
	parentTitle = kbArticle.getParentTitle(locale, status);
	priority = kbArticle.getPriority();

	if (kbArticle.isApproved()) {
		targetStatus = WorkflowConstants.STATUS_APPROVED;
	}
}
else {
	KBFolder kbFolder = KBFolderServiceUtil.getKBFolder(resourcePrimKey);

	title = kbFolder.getName();
	parentTitle = kbFolder.getParentTitle(locale);
}

boolean portletTitleBasedNavigation = GetterUtil.getBoolean(portletConfig.getInitParameter("portlet-title-based-navigation"));

if (portletTitleBasedNavigation) {
	portletDisplay.setShowBackIcon(true);
	portletDisplay.setURLBack(redirect);

	renderResponse.setTitle(title);
}
%>

<c:if test="<%= !portletTitleBasedNavigation %>">
	<liferay-ui:header
		backURL="<%= redirect %>"
		localizeTitle="<%= false %>"
		title="<%= title %>"
	/>
</c:if>

<div <%= portletTitleBasedNavigation ? "class=\"container-fluid container-fluid-max-xl container-form-lg\"" : StringPool.BLANK %>>
	<liferay-portlet:actionURL name="/knowledge_base/move_kb_object" var="moveKBObjectURL" />

	<aui:form action="<%= moveKBObjectURL %>" method="post" name="fm">
		<aui:input name="mvcPath" type="hidden" value="/admin/common/move_object.jsp" />
		<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
		<aui:input name="resourceClassNameId" type="hidden" value="<%= String.valueOf(resourceClassNameId) %>" />
		<aui:input name="resourcePrimKey" type="hidden" value="<%= String.valueOf(resourcePrimKey) %>" />
		<aui:input name="parentResourceClassNameId" type="hidden" value="<%= String.valueOf(parentResourceClassNameId) %>" />
		<aui:input name="parentResourcePrimKey" type="hidden" value="<%= String.valueOf(parentResourcePrimKey) %>" />
		<aui:input name="status" type="hidden" value="<%= String.valueOf(status) %>" />

		<liferay-ui:error exception="<%= KBArticlePriorityException.class %>" message='<%= LanguageUtil.format(request, "please-enter-a-priority-that-is-greater-than-x", "0", false) %>' translateMessage="<%= false %>" />

		<aui:fieldset-group markupView="lexicon">
			<aui:fieldset>
				<aui:field-wrapper label="current-parent">
					<aui:input label="" name="currentParentTitle" readonly="<%= true %>" value="<%= parentTitle %>" />

					<aui:input cssClass="input-mini" label="priority" name="currentPriority" readonly="<%= true %>" value="<%= BigDecimal.valueOf(priority).toPlainString() %>" />
				</aui:field-wrapper>

				<aui:field-wrapper label="new-parent">
					<div id="<portlet:namespace />newParent">
						<aui:input label="" name="parentTitle" readonly="<%= true %>" value="<%= parentTitle %>" />

						<aui:input cssClass="input-mini" id="parentPriority" label="priority" name="priority" type="text" value="<%= BigDecimal.valueOf(priority).toPlainString() %>" />
					</div>

					<aui:button name="selectKBObjectButton" value="select" />
				</aui:field-wrapper>
			</aui:fieldset>

			<div class="sheet-footer">
				<aui:button type="submit" value="move" />

				<aui:button href="<%= redirect %>" type="cancel" />
			</div>
		</aui:fieldset-group>
	</aui:form>
</div>

<script>
	var selectKBObjectButton = document.getElementById(
		'<portlet:namespace />selectKBObjectButton'
	);

	if (selectKBObjectButton) {
		selectKBObjectButton.addEventListener('click', (event) => {
			Liferay.Util.openSelectionModal({
				onSelect: function (event) {
					Liferay.Util.setFormValues(document.<portlet:namespace />fm, {
						parentPriority: event.priority,
						parentResourceClassNameId: event.resourceclassnameid,
					});

					var folderData = {
						idString: 'parentResourcePrimKey',
						idValue: event.resourceprimkey,
						nameString: 'parentTitle',
						nameValue: event.title,
					};

					Liferay.Util.selectFolder(folderData, '<portlet:namespace />');
				},
				selectEventName: '<portlet:namespace />selectKBObject',
				title: '<liferay-ui:message key="select-parent" />',

				<liferay-portlet:renderURL var="selectKBObjectURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
					<portlet:param name="mvcPath" value="/admin/common/select_parent.jsp" />
					<portlet:param name="resourceClassNameId" value="<%= String.valueOf(resourceClassNameId) %>" />
					<portlet:param name="resourcePrimKey" value="<%= String.valueOf(resourcePrimKey) %>" />
					<portlet:param name="parentResourceClassNameId" value="<%= String.valueOf(parentResourceClassNameId) %>" />
					<portlet:param name="parentResourcePrimKey" value="<%= String.valueOf(parentResourcePrimKey) %>" />
					<portlet:param name="originalParentResourcePrimKey" value="<%= String.valueOf(parentResourcePrimKey) %>" />
					<portlet:param name="priority" value="<%= String.valueOf(priority) %>" />
					<portlet:param name="status" value="<%= String.valueOf(status) %>" />
					<portlet:param name="targetStatus" value="<%= String.valueOf(targetStatus) %>" />
				</liferay-portlet:renderURL>

				url: '<%= HtmlUtil.escapeJS(selectKBObjectURL) %>',
			});
		});
	}
</script>