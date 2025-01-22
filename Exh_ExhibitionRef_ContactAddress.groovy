def outputArray = []
def refs = record?.ExhParticipantRef
def item = null;
if (refs.size() == 1){
   item = refs[0];  
} else {
   refs?.each{
      if (it?.'[TypeVoc]'?.logicalName == 'Leihnehmer:in Kontakt'){
         item = it;
      }
   }
} 
if (item){
   def gender = (item?.AdrTitleVoc?.logicalName) ? item?.AdrTitleVoc?.logicalName + ' ' : '';
   def acTitle = (item?.AdrAcademicTitleVoc?.logicalName) ? item?.AdrAcademicTitleVoc?.logicalName + ' ': '';
   def forename = item?.AdrForeNameTxt;
   def surename = item?.AdrSurNameTxt;
   outputArray <<  gender + acTitle + forename + ' ' + surename
   if (item?.AdrFunctionVoc?.size() > 0) {
     outputArray << item?.AdrFunctionVoc?.join(' ') 
   }
   outputArray << item?.AdrOrganisationTxt;
   outputArray << item?.AdrStreetTxt;
   def postcode = (item?.AdrPostcodeTxt) ? item?.AdrPostcodeTxt + ' ' : '';
   def city = (item?.AdrCityTxt) ? item?.AdrCityTxt : '';
   outputArray << postcode + city;
   def country =  (item?.AdrCountryVoc?.getLabel('en')) ? item?.AdrCountryVoc?.getLabel('en') : item?.AdrCountryVoc?.getLabel()


   outputArray << country 

}
output = outputArray?.join('\n')
