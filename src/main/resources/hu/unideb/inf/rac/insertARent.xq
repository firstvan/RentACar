let $getSiteByName := function($nev) {
   for $site in doc('rentacardb')/company/sites/site
   where $site/address/city = $nev
   return $site
}

let $getNextRentId := function() {
  max(for $id in doc('rentacardb')/company/sites/site/rents/rent/id/text()
  return xs:integer($id)) + 1
}

let $getPersonId := function($licNum) {
  doc('rentacardb')/company/persons/person[driverLicense eq $licNum]/@id
}

let $getCar := function($licPlateNum) {
  doc('rentacardb')/company/cars/car[licensePlateNumber eq $licPlateNum]/@id
}

return insert node element rent {
  element id { $getNextRentId() },
  element person { attribute idrefs {$getPersonId("MJ027225")} },
  element from { "2017-11-10" },
  element to { "2017-12-02" },
  element distanceUnit { "Metric" },
  element car { attribute idrefs { $getCar("XJN-961") } }
} into $getSiteByName('Budapest')/rents