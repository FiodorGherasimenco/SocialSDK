/*
 * Copyright IBM Corp. 2014
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at:
 * 
 * http://www.apache.org/licenses/LICENSE-2.0 
 * 
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or 
 * implied. See the License for the specific language governing 
 * permissions and limitations under the License.
 */

/**
 * LockTopicAction
 */
define([ "../../../declare", "../../../dom", "../../../lang",
         "../../../i18n!./nls/ForumView", "./LockTopicWidget", 
         "../../../controls/dialog/Dialog", "../../../controls/view/Action",
         "../../ForumService"], 
	function(declare, dom, lang, nls, LockTopicWidget, Dialog, Action,ForumService) {

	/**
	 * Action to Lock a forum topic
	 * 
	 * @class DeleteTopicAction
	 * @namespace sbt.connections.controls.forums
	 * @module sbt.connections.controls.forums.LockTopicAction
	 */
	var LockTopicAction = declare([ Action ], {
		
		name : nls.lockTopic,
	
		/**
		 * Set topics on the associated widget. 
		 */
		selectionChanged : function(state, selection, context) {
			this.inherited(arguments);
			
			if (this.widget) {
				this.widget.selectionChanged(selection, context);
			}
			
			if(selection.length > 0){
				var forumService = this.getForumService();
				var topic = forumService.newForumTopic(selection[0]);
				var isLocked = topic.isLocked();
				if(isLocked){
					this.actionNameNode.textContent = nls.unlockTopic;
					this.name = nls.unlockTopic;	
				}else {
					this.actionNameNode.textContent = nls.lockTopic;
					this.name = nls.lockTopic;
				}
			}
			
		},

		/**
		 * Return the ForumService.
		 */
		getForumService : function() {
			if (!this.forumService) {
				var args = this.endpoint ? { endpoint : this.endpoint } : {};
				this.forumService = new ForumService(args);
			}
			return this.forumService;
		},
		/**
		 * Only enabled when at least one topic is selected.
		 */
		isEnabled : function(selection, context) {
			return (selection.length > 0 && selection.length < 2);
		},

		/**
		 * Open dialog to upload a file.
		 */
		execute : function(selection, context) {
			var self = this;
			var widgetArgs = lang.mixin({
				hideButtons: true,
    			topics: selection,
    			view: this.view,
				displayMessage : function(template, isError) {
					self.displayMessage(template, isError);
				}
			}, this.widgetArgs || {});
			var widget = new LockTopicWidget(widgetArgs);

    		var dialog = this.showDialog(widget,{ OK: nls.ok },this.dialogArgs);
		}
	});

	return LockTopicAction;
});