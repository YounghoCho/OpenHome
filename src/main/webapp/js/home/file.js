  $(function(){
            // Reset the baseUrl of template manager
            Backbone.TemplateManager.baseUrl = '{name}';
            
            // Create the upload manager object
	        var uploadManager = new Backbone.UploadManager({
	            uploadUrl: 'http://upload-manager.sroze.io/upload',
	            templates: {
	                main: '../../../OpenHome/backbone/templates/upload-manager.main',
	                file: '../../../OpenHome/backbone/templates/upload-manager.file'
	            }
	        });
            
            // Render it in our div
	        uploadManager.renderTo($('div#manager-area'));
    });