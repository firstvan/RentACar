declare variable $eDate as xs:date external;
declare variable $eMfd as xs:integer external;

let $getCarsCount := function($date, $mfd) {
  element { "result" } {count(let $basePath := doc('rentacardb')/company
      for $suv in $basePath/sites/site/rentableCars/suvs/*/vehicles/car,
          $rented in $basePath/sites/site/rents/rent,
          $car in $basePath/cars/car
      where $suv/@idrefs = $rented/car/@idrefs and $rented/from > $date and 
            $suv/@idrefs = $car/@id and $car/mfd > $mfd
      return $suv)}
}

return element { "results" } {$getCarsCount($eDate, $eMfd)}