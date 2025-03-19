def outputArray = []

def accessories = record?.ObjAccessoryRef

accessories.each{

  output = it?.AccNumberTxt
}
