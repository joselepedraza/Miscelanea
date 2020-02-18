<?php


  function conecta()
  {
      $DB = new mysqli("localhost","tdiw-a2","ieheisoz","tdiw-a2");

      if ($DB->connect_error)
      {
          die("Connection failed: " . $DB->connect_error);
      }
      else
      {
          return $DB;
      }
	}
?>