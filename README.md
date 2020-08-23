# FightClub   
## Architecture      
### MVVM 
- ViewModels by presentation layer, to request for data 
- ViewModels asks respective use cases injected in it to return  data that can be parsed to a viewstate
- Usecases in turn has repositories injected which abstracts the data source, they may contain specific business logic if reuired
- Repositories talk to network or memory cache ad get data in specific format back to usecase
- Viewmodels then give back a viewstate which is parsed by the View

 ## Mainactivity 
 - Has 2 fragments - 1 for movies listing and the other for searching
 - They both use the same viewmodel
 
 ## Search algo
 ### Indexig
 - Weight of each word is word is calculated (hashCode is the weight in this case)
 - All results containing that specific word is linked to it
 - First alphabet of each word indexed is associated to all such weghts, which inturn contains all the results
 ### Searching
  - If a single character is inserted, all liked hashes are traversed
  - For every linked hash, all the results related to the hash are collated
  - Then the uniques results are found are returned
  - If a word is searched, the weight of the search term is calculated and all linked results are returned
  - If more than a word is seached - individual hashes are computed
  - Results linked to each hash are collated and unique results are returned
  
  ## MovieDetailActivity
  - Single Activity shows a view combining data of different Api's

 
