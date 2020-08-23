### FightClub   
## Architecture      
# MVVM 
- ViewModels are used to to requst data by presentation layer 
- ViewModels asks respective use cases injected in it to return a data that can be parsed to a viewstate
- usecases in turn has repositories injected which abstracts the data source, they may contain specific business logic if reuired
- repositories talk to network or memory cache ad get data in specific format back to usecase
 # Mainactivity 
 - has 2 fragments - 1 for movies listing and one for searching
 
 ## Search algo
 # Indexig
 - weight of each word is word is calculated (hashCode is the weight in this case)
 - all results containing that specific word is linked to it
 - first alphabet of each word indexed is associated to all such weghts, which inturn contains all the results
 # Searching
  - if a single character is inserted, all liked hashes are traversed
  - for every linked hash, all the results related to the hash are collated
  - then the uniques results are found are returned
  - if a word is searched, the weight of the search term is calculated and all linked results are returned
  - if more than a word is seached - individual hashes are computed
  - results linked to each hash are collated and unique results are returned
  
  # MovieDetailActivity
  - single Activity shows a view combining data of different Api's

 
