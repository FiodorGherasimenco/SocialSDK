/*
 * � Copyright IBM Corp. 2013
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
 * 
 */
define([ "../../declare", "../../controls/grid/GridRenderer",
        "../../text!../../controls/grid/templates/GridPager.html", 
        "../../text!../../controls/grid/templates/GridSorter.html",
        "../../text!../../controls/grid/templates/SortAnchor.html",
        "../../i18n!./nls/BaseGridRenderer" ],
        function(declare, GridRenderer, GridPager, GridSorter, SortAnchor, nls) {

    /**
     * @module sbt.smartcloud.controls.BaseGridRenderer
     * @class BaseGridRenderer
     * @namespace sbt.smartcloud.controls
     */
    var BaseGridRenderer = declare(GridRenderer, {
    	
    	/**Strings used in the grid*/
        nls : {},
        /**CSS class for tables*/
        tableClass : "lotusTable",
        /**CSS Class for empty icon*/
        emptyClass : "lconnEmpty",
        /**CSS Class for an error on an icon*/
        errorClass : "lconnEmpty",
        /**The css class to use when the grid is loading,null here as an image is used instead*/
        loadingClass : "",
        /**The loading image*/
        loadingImgClass : "lotusLoading",
        /**The css class for the first row of the grid*/
        firstClass : "lotusFirst",
        /**CSS classes for sorting*/
        defaultSortClass : "lotusActiveSort lotusDescending",
        ascendingSortClass : "lotusActiveSort lotusAscending",
        descendingSortClass : "lotusActiveSort lotusDescending",
        /**The HTML template to use to show paging (moving forward and backward through sets of results)*/
        pagerTemplate : GridPager,
        /**The HTML template to show sorting options*/
        sortTemplate : GridSorter,
        /**The HTML template for sort Anchors*/
        sortAnchor : SortAnchor,

        /**
         * Merge this class with the GridRenderer Class
         * @method constructor
         * @param args
         */
        constructor : function(args) {
            this._mixin(this, args);
            this.nls = this._mixin(nls, this._nls);
        }

    });

    return BaseGridRenderer;
});