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

require_once("../../init.php");

$datatypes = Array();
$datatypes['String'] = 0;
$datatypes['char'] = 1;
$datatypes['int'] = 2;
$datatypes['long'] = 3;
$datatypes['double'] = 4;
$datatypes['boolean'] = 5;
$datatypes['Date'] = 6;

class JColumn {
	var $name;
	var $sqlType;
	var $size;
	var $javaType;
	
	function JColumn($name, $sqlType) {
		$this->name = $name;
		$this->sqlType = $sqlType;
		
		$ac = explode("(", $sqlType);
		$this->size = intval(str_replace(")", "", $ac[1]));
		
		switch ($ac[0]) {
			case "varchar":
				$this->javaType = "String";
			break;
			case "double":
				$this->javaType = "double";
			break;
			case "bigint":
				$this->javaType = "long";
			break;
			case "int":
				$this->javaType = "int";
			break;
			case "tinyint":
				$this->javaType = "boolean";
			break;
			case "date":
				$this->javaType = "Date";
			break;
			case "datetime":
				$this->javaType = "Date";
			break;
			case "timestamp":
				$this->javaType = "Date";
			break;
			case "char":
				if($this->size > 1)
//					$this->javaType = "char[]";
					$this->javaType = "String";
				else
					$this->javaType = "char";
			break;
			case "text":
				$this->javaType = "String";
			break;
			
			default:
				$this->javaType = "Buggered_If_I_Know";;
			break;
		}
	}
	
	function toString() {
		return "name: " . $this->name . ", javaType: " . $this->javaType;
	}
	function toJavaDef() {
		return "\tprivate " .$this->javaType . " " . $this->name . ";\n";
	}
	function toJavaInitialize() {
		$str = "\t\t" . $this->name . " = attributes.get";
		switch ($this->javaType) {
			case "String":
				$str.= "String";
			break;
			case "int":
				$str.= "Int";
			break;
			case "long":
				$str.= "Long";
			break;
			case "double":
				$str.= "Double";
			break;
			case "boolean":
				$str.= "Boolean";
			break;
			case "Date":
				$str.= "Date";
			break;
			case "char":
				$str.= "char";
			break;
//			case "char[]":
//				$str.= "CharArray";
//			break;
			
			default:
				$str.= "Buggered_If_I_Know";
			break;
		}
		$str.= "Value(\"" . $this->name . "\");\n";
		return $str;
	}
	function toJavaSerialize() {
		return "\t\tattributes.put(\"" . $this->name . "\", " . $this->name . ");\n";
	}
	function toJavaPutSchema() {
		global $datatypes;
		$dt = $datatypes[$this->javaType];
		$str = "put(new AttributeSchema($dt, \"$this->name\", \"$this->name\", null, null, $this->size));";
		$str.= "\n";
		return $str;
	}
}

class JObjectClass {
	
	var $jcolumns = Array();
	var $javaClassName;
	var $javaPackageName;
	var $tableName;
	var $dbName;
	var $db;
	
	function JObjectClass($javaClassName, $javaPackageName, $tableName, $dbName, &$db) {
		$this->javaClassName = $javaClassName;
		$this->javaPackageName = $javaPackageName;
		$this->tableName = $tableName;
		$this->dbName = $dbName;
		$this->db = &$db;
	}
	
	function toCSObjectJava() {
		
		if($this->dbName) {
			$sql = "show columns from " . $this->dbName . "." . $this->tableName;
		}
		else {
			$sql = "show columns from " . $this->tableName;
		}
		
		$this->db->setQuery($sql);
		$rows = $this->db->loadRowList();
		
//		dump($rows);

		foreach ($rows as $row) {
			$this->jcolumns[] = new JColumn($row[0], $row[1]);
		}
		
		$java = "\npackage $this->javaPackageName;\n";
		
		$java.= "\n\npublic class " . $this->javaClassName . " extends RemoteObject {\n\n";
		
		foreach ($this->jcolumns as $jc) {
			$java.= $jc->toJavaDef();
		}
		
$java.=
"
	///////////////////////////////////////////////////////////////////////////////////////

	public ObjectSchema getObjectSchema() {
		ObjectSchema os = new ObjectSchema();
";

		foreach ($this->jcolumns as $jc) {
			$java.= "\t\tos." . $jc->toJavaPutSchema();
		}

$java.= 
"
		return os;
	}

	///////////////////////////////////////////////////////////////////////////////////////

";
		
		
		$java.= "\n\n\tprotected void onInitialize(Attributes attributes) {\n";
//		$java.= "\t\tsuper.onInitialize(attributes);\n";
		foreach ($this->jcolumns as $jc) {
			$java.= $jc->toJavaInitialize();
		}
		$java.= "\t}\n\n";

		$java.= "\tprotected void onSerialize(Attributes attributes) {\n";
//		$java.= "\t\tsuper.onSerialize(attributes);\n";
		foreach ($this->jcolumns as $jc) {
			$java.= $jc->toJavaSerialize();
		}
		$java.= "\t}\n\n}";
		return $java;
	}
	
}

$javaClassName = $_REQUEST['javaClassName'];
$javaPackageName = $_REQUEST['javaPackageName'];
$tableName = $_REQUEST['tableName'];
$dbName = $_REQUEST['dbName'];

echo "<pre>";

$joc = new JObjectClass($javaClassName, $javaPackageName, $tableName, $dbName, &$database);

echo $joc->toCSObjectJava();

echo "</pre>";




?>

