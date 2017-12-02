declare variable $eFrom as xs:date external;
declare variable $eTo as xs:date external;
declare variable $eSiteId as xs:string external;

let $getSites := function($site) {
   for $a in (1)
    return if ($site eq "") then
              doc('rentacardb')/company/sites/site/rents/rent
           else
              doc('rentacardb')/company/sites/site[@id eq $site]/rents/rent
}

let $getCars := function($from, $to, $siteId) {
      for $rent in $getSites($siteId)
      where ($rent/from > $from and $rent/to < $to)
      order by $rent/from
      return for $car in doc('rentacardb')/company/cars/car
             where $car/@id = $rent/car/@idrefs
             return element { "result"} {($rent/from, $rent/to, $car) }
}

return element {"results"} {$getCars($eFrom, $eTo, $eSiteId)}