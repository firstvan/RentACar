let $deleteRent := function() {
  for $person in doc('rentacardb')/company/persons/person
  where $person/name eq "Suzi Murford"
  return for $site in doc('rentacardb')/company/sites/site
  where $site/address/city eq "Budapest"
  return for $rent in $site/rents/rent
  where $rent/person/@idrefs = $person/@id and $rent/from="2016-12-16" and $rent/to="2017-10-12"
  return $rent
}

return delete node $deleteRent()
