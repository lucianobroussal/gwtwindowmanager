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


////////////////////////////////////////////////////////////////////////////////////////////////

class GenericDBTable extends mosDBTable {

	var $_schema;
	var $_className;
	var $_packageName;
	
	function GenericDBTable($className, $packageName, $tableName, &$database) {
		$this->mosDBTable($tableName, "id", &$database);
		$this->_className = $className;
		$this->_packageName = $packageName;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	/**
	 * 
	 */
	function _setSchema( $array=null ) {
		if (is_array( $array )) {
			$this->_schema = $array;
		} else {
			$tableFields = $this->_db->getTableFields( array( $this->_tbl ) );
			$this->_schema = $tableFields[$this->_tbl];
		}
	}

	/**
	 * 
	 */
	function getSchema() {
		if ($this->_schema == null) {
			$this->_setSchema();
		}
		return $this->_schema;
	}

	////////////////////////////////////////////////////////////////////////////////////////////////

	function listObjects($filter) {
		$columns = $filter->columns ? implode(",", $filter->columns) : "*";
		$sql = "select $columns from $this->_tbl";
		$sql.= $filter->toSQL();
		$this->_db->setQuery($sql);
		$rows = $this->_db->loadRowList();
		return new ResultList($filter, $rows);
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////
	
	function checkRequiredFields($csl, &$errors) {
		$fieldNames = explode(",", $csl);
		foreach ($fieldNames as $name) {
			$n = trim($name);
			if(!property_exists($this, $n)) {
				$errors["$n"] = "No field exists with name: $n";
			}
			else {
				$val = $this->$n;
				if(!$val) {
					$errors["$n"] = "Field \"$n\" is required";
				}
			}
		}
	}

	////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * Generate a PHP class source file.
	 * 
	 * @return a string containing text or false on error.
	 */
	function generatePHP($outFilePath=false) {
		$code = "\n";
		
		$code.= "// no direct access\n";
		$code.= "if(!defined( '_VALID_MOS' )) { die( 'Restricted access' ); }\n\n";

		$code.= "class $this->_className extends GenericDBTable {\n\n";
		
		$schema = $this->getSchema();
		foreach ($schema as $name => $type) {
			$code.= "\t/** @var $name : $type */\n";
			$code.= "\tvar $" . $name .";\n";
		}

		$code.= "\n\t///////////////////////////////////////////////////////////////////////////////////\n\n";

		$code.= "\tfunction $this->_className(&\$database) {\n";
		$code.= "\t\t\$this->GenericDBTable('$this->_className', '$this->_packageName', '$this->_tbl', &\$database);\n\t}\n\n";

		$code.= "\n\t///////////////////////////////////////////////////////////////////////////////////\n\n";
		
		$code.= "\t/** Dont change or override this
\t * It's needed to determine table info (like field names).
\t */\n";
		$code.= "\tfunction _getTableBaseClass() {\n\t\treturn get_class();\n\t}\n";
		
		$code.= "\tfunction _getRemoteObjectType() {\n\t\treturn '$this->_packageName.$this->_className';\n\t}\n";
		
		$code.= "\n\t///////////////////////////////////////////////////////////////////////////////////\n\n";

		$code.= "}\n\n";

		if($outFilePath) {
			$handle = fopen("$outFilePath/$this->_className.php", "w");
			fwrite($handle, "<?php\n".$code."\n?>\n");
			fclose($handle);
		}

		return $code;
	}

	////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * 
	 */
	function initialize($array) {
		foreach ($array as $name => $value) {
			// if prop exists for this class, just set it
			if(property_exists($this->_getTableBaseClass(), $name)) {
				$this->$name = $value;
			}
			// else delegate to sub-class (if any)
			else {
				$this->onInitializeField($name, $value);
			}
		}
	}

	/**
	 * Called by initialize method if field not defined in base class (i.e. table).
	 * Should be over-ridden by subclasses to handle any additional props, like child objects.
	 */
	function onInitializeField($name, $value) {}
	
	/**
	 * 
	 */
	function serialize($attributes=null) {
		if(!$attributes) {
			$attributes = array();
		}
		$attributes['__remoteObjectType__'] = $this->_getRemoteObjectType();
		// get all the vars from this instance
		$fields = get_class_vars(get_class($this));
		foreach ($fields as $name => $value) {
			// not interested in vars beginning with underscore...
			if(substr($name, 0, 1) != "_") {
				$attributes[$name] = $this->_serialize($this->$name);
			}
		}
		return $attributes;
	}
	function _serialize($fieldValue) {
		$val = $fieldValue;
		if(is_object($val)) {
			$fields = get_class_vars(get_class($val));
			$val = array();
			foreach ($fields as $name => $value) {
				// not interested in vars beginning with underscore...
				if(substr($name, 0, 1) != "_") {
					$val[$name] = $this->_serialize($this->$name);
				}
			}
		}
		return $val;
	}

	/**
	 * Called by initialize method if field not defined in base class (i.e. table).
	 * Should be over-ridden by subclasses to handle any additional props, like child objects.
	 * 
	 */
	function onSerializeField($name, $value, $attributes) {}

	////////////////////////////////////////////////////////////////////////////////////////////////

}






?>