<?php
 	$sFileInfo = '';
	$headers = array(); 
	foreach ($_SERVER as $k => $v){   
  	
		if(substr($k, 0, 9) == "HTTP_FILE"){ 
			$k = substr(strtolower($k), 5); 
			$headers[$k] = $v; 
		} 
	}
	
	$file = new stdClass; 
	$file->name = rawurldecode($headers['file_name']);	
	$file->size = $headers['file_size'];
	$file->content = file_get_contents("php://input"); 
	
	$newPath = $_SERVER['DOCUMENT_ROOT'].'/nse/myPhoto/'.iconv("utf-8", "cp949", $file->name);
	
	if(file_put_contents($newPath, $file->content)) {
		$sFileInfo .= "&bNewLine=true";
		$sFileInfo .= "&sFileName=".$file->name;
		$sFileInfo .= "&sFileURL=/nse/myPhoto/".$file->name;
	}
	echo $sFileInfo;
 ?>
