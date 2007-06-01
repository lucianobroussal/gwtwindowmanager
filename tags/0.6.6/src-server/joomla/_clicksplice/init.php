<?php
/*
 * Copyright (c) 2006/2007 Flipperwing Ltd. (http://www.flipperwing.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

/**
 * @author andy.scholz@gmail.com
 * @copyright (c)2006-2007 Flipperwing Ltd.
 */

// Set flag that this is a parent file
define( '_VALID_MOS', 1 );

$siteRootDir = realpath(dirname(__FILE__)."/../");
$csIncludesDir = realpath(dirname(__FILE__)."/includes");
$csControllersDir = realpath(dirname(__FILE__)."/controllers");
$csModelsDir = realpath(dirname(__FILE__)."/models");
$csJavaModelsDir = realpath(dirname(__FILE__)."/javaModels");

include_once( $siteRootDir.'/globals.php' );
require_once( $siteRootDir.'/configuration.php' );
require_once( $siteRootDir.'/includes/joomla.php' );

require_once($csIncludesDir.'/base.inc.php');
require_once($csIncludesDir.'/JSON.php');

define('DEFAULT_PAGE_SIZE', 30);

function json_encode($val) {
	$json = new Services_JSON();
	return $json->encode($val);
}

function json_decode($val) {
	$json = new Services_JSON();
	return $json->decode($val);
}

define('DEFAULT_PAGE_SIZE', 30);

function dump($thing) {
	echo debug_zval_dump($thing);
}

/////////////////////////////////////////////////////////////////////////////////////////

function readData($obj=null) {
	$input = file_get_contents('php://input', 10000000);
	$attrs = json_decode($input);
	if($obj && method_exists($obj, "initialize")) {
		$obj->initialize($attrs);
	}
	else {
		return $attrs;
	}
}

function returnData($data) {
	if(method_exists($data, "serialize")) {
		echo json_encode($data->serialize());	
	}
	else {
		echo json_encode($data);
	}
	exit;
}

function returnError($message=null, $attributes=null) {
	returnData(new RemoteStatus(STATUS_ERROR, $message, $attributes));
}

function returnValidationError($message=null, $attributes=null) {
	returnData(new ValidationError($message, $attributes));
}

function returnOK($message=null, $attributes=null) {
	returnData(new RemoteStatus(STATUS_OK, $message, $attributes));
}

/////////////////////////////////////////////////////////////////////////////////////////

define('STATUS_OK', 0);
define('STATUS_WARNING', 1);
define('STATUS_ERROR', -1);
define('STATUS_VALIDATION_ERROR', -2);
	
/**********************************************************
 * common class defs
 */

class RemoteObject {
	var $__remoteObjectType__;
}

class RemoteStatus extends RemoteObject {
	var $status = STATUS_OK;
	var $message;
	var $attributes;
	
	function RemoteStatus($status=STATUS_OK, $message=null, $attributes=null) {
		$this->status = $status;
		$this->message = $message;
		$this->attributes = $attributes;
		$this->__remoteObjectType__ = "org.gwm.splice.client.service.data.RemoteStatus";
	}
}
	
class ValidationError extends RemoteStatus {
	
	function ValidationError($message=null, $attributes=null) {
		$this->status = STATUS_VALIDATION_ERROR;
		$this->message = $message;
		$this->attributes = $attributes;
		$this->__remoteObjectType__ = "org.gwm.splice.client.service.data.ValidationError";
	}
}
	

class ResultList extends RemoteObject {
	var $rows;
	
	function ResultList($filter, $rows) {
		$this->setFilter($filter);
		$this->setRows($rows);
		$this->__remoteObjectType__ = "org.gwm.splice.client.service.data.ResultList";
	}
	
	function getFilter() {
		return $this->attributes['filter'];
	}
	function setFilter($filter) {
		$this->attributes['filter'] = $filter;
	}
	function getRows() {
		return $this->rows;
	}
	function setRows($rows) {
		$this->rows = $rows;
	}
}

class SortOrder {
	var $column;
	var $direction;
	
	function SortOrder($csl) {
		$aa = explode(",", $csl);
		$this->column = $aa[0];
		$this->direction = $aa[1];
	}
	function toSQL() {
		$sql = "\nORDER BY " . $this->column;
		if($this->direction == 1) {
			$sql.= " DESC";
		}
		return $sql;
	}
}

class Filter {
	
	var $pageSize = DEFAULT_PAGE_SIZE;
	var $startPage;
	var $sortOrder;
	var $columns;
	var $filter;
	
	function Filter($request) {
		if($request['pageSize']) {
			$this->pageSize = (int)$request['pageSize'];
		}
		if($request['startPage']) {
			$this->startPage = (int)$request['startPage'];
		}
		if($request['sortOrder']) {
			$this->sortOrder = new SortOrder($request['sortOrder']);
		}
		if($request['columns']) {
			$this->columns = explode(",", $request['columns']);
		}
		if($request['filter']) {
			$this->filter = stripslashes($request['filter']);
		}
	}
	function toSQL() {
		$sql = "";
		if($this->filter) {
			$sql.= "\nWHERE ".$this->filter;
		}
		if($this->sortOrder) {
			$sql.= $this->sortOrder->toSQL();
		}
		
		if($this->startPage) {
			$sql.= "\nLIMIT ".$this->startPage * $this->pageSize.",".$this->pageSize;
		}
		else if($this->pageSize) {
			$sql.= "\nLIMIT ".$this->pageSize;
		}
		
		return $sql;
	}
}

class SecuredRemoteObject extends RemoteObject {
	var $currentUserAccessFlags = 0;	
}

class ContentNode extends SecuredRemoteObject {
	var $id;
	var $parentId;
	var $name;
	var $type;
	var $left;
	var $top;
	var $description;
	var $image;
	var $ownerId;
	var $groupId;
	var $groupAccess;
	var $publicAccess;
	var $hits;
	var $created;
	var $modified;
	var $mimetype;
	var $initAttributes;
	var $attributes;
	var $schemaId;
	var $lockedBy;
	var $lockDate;
	var $lockExpires;
	var $lockComment;
	var $latestVersion;
	var $currentVersion;
	var $publishStart;
	var $publishEnd;

	function ContentNode() {
		$this->__remoteObjectType__ = "org.gwm.splice.client.content.ContentNode";
	}
	
}

?>