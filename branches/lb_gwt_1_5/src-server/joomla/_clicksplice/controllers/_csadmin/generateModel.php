<?/*
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


$className = $_REQUEST['className'];
$packageName = $_REQUEST['packageName'];
$tableName = $_REQUEST['tableName'];
$dbName = $_REQUEST['dbName'];


$mdt = new GenericDBTable($className, $packageName, $tableName, &$database);

$code = $mdt->generatePHP($csModelsDir);



echo "Generating Model " . $className;

echo "<pre>";

echo $code;

echo "</pre>";




?>

