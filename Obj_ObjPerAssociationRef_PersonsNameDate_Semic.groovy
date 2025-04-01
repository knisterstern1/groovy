def outputArray = []
def myList = fetch('ObjPerAssociationRef')?.collect()
def sortedList = myList?.size() ? myList?.sort{a,b -> fetch('[SortLnu]', null, a) <=> fetch('[SortLnu]', null, b)} : null

sortedList?.each{
	def attribution = fetch('[AttributionVoc]', null, it)
	switch(fetch('PerTypeVoc', null, it)?.logicalName){
		case 'artist':
			outputArray << combine(
                                fetch('PerTitleTxt', null, it), ' ',
				fetch('PerForeNameTxt', null, it), ' ',
				fetch('PerSurNameTxt', null, it), ' ',
                                attribution ? [attribution] : null, ' ',
				fetch('PerDatingTxt', null, it) ? '(' + fetch('PerDatingTxt', null, it) + ')':''		
			)
		break
		case 'organization':
			outputArray << combine(
				fetch('PerOrganisationMainBodyTxt', null, it), ' ',
                                attribution ? [attribution] : null, '',
				fetch('PerDatingTxt', null, it) ? ', (' + fetch('PerDatingTxt', null, it) + ')':''
			)
		break
		case 'group':
			outputArray << combine(
				fetch('PerNameTxt', null, it), ' ',
                                attribution ? [attribution] : null, '',
				fetch('PerDatingTxt', null, it) ? ', (' + fetch('PerDatingTxt', null, it) + ')':''
			)
		break
     }
}
print outputArray?.join('; ')
