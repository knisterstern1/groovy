def getMeasure(Object measure) {
    def measureParts = []
    def label = measure?.MeasureType3DimVoc
    if (measure?.FirstNum) {
      measureParts << measure?.FirstNum
    }
    if (measure?.SecondNum) {
      measureParts << measure?.SecondNum
    }
     if (measure?.ThirdNum) {
      measureParts << measure?.ThirdNum
    }
  
    def measures = measureParts.join(" x ")
    return [label, measures].join(": ") 
}
def getRootAccessory(Object obj) {

}

def outputArray = []
def accessories = record?.ObjParentAccessoryRef

accessories.each{
  def item = []
  if (it?.AccDenominationVoc) {
    item << it?.AccDenominationVoc 
  }
  item << it?.AccNumberTxt
  if (it?.AccDimAllGrp.size() > 0){
    item << getMeasure(it?.AccDimAllGrp.findAll().first()) 
  }
 outputArray << item.join(", ")
}
output = outputArray.join("\n")
