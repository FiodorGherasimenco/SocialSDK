require(["sbt/dom", "sbt/connections/controls/files/FileGrid"], function(dom, FileGrid) {
        var grid = new FileGrid({
	         type : "fileShares",
	         direction : "outbound"
	    });
		         
	    dom.byId("gridDiv").appendChild(grid.domNode);
		         
	    grid.update();
});