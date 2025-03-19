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
    return (obj?.AccParentAccessoryRef) ? obj?.AccParentAccessoryRef : obj
}

def outputArray = []
def accessory = record?.ObjParentAccessoryRef
if (accessory) {
  def root = getRootAccessory(accessory)   
  def item = []
  if (root?.AccDenominationVoc) {
    item << root?.AccDenominationVoc 
  }
  item << root?.AccNumberTxt
  if (root?.AccDimAllGrp.size() > 0){
    item << getMeasure(root?.AccDimAllGrp.findAll().first()) 
  }
 outputArray << item.join(", ")
}
output = outputArray.join("\n")
