import re

import sys
 
fileToUpdate = sys.argv[1]

print(fileToUpdate)

temporaryReplaceStringBegin="22222"

temporaryReplaceStringEnd="44444"

def replaceCSSIncludes():

	templateSectionBegin="<!-- html / head / css / begin -->"

	templateSectionEnd="<!-- html / head / css / end -->"

	with open('../.template-parts/html-head-css.template') as templateContentFile:

		contentToSubstituteIntoArea = templateContentFile.read()

	with open(fileToUpdate) as targetSubstitutionFile:
		s = targetSubstitutionFile.read()

	ddd=s

	ddd=re.sub(templateSectionBegin, templateSectionBegin+temporaryReplaceStringBegin, ddd, flags=re.DOTALL)

	ddd=re.sub(templateSectionEnd, temporaryReplaceStringEnd+templateSectionEnd, ddd, flags=re.DOTALL)
	ddd=re.sub(temporaryReplaceStringBegin+".*"+temporaryReplaceStringEnd,contentToSubstituteIntoArea,ddd,flags=re.DOTALL)

	f = open(fileToUpdate, "w")
	
	f.write(ddd)
	
	f.close()

def replaceJavascriptIncludes():

	templateSectionBegin="!-- html / body / javascript / begin -->"

	templateSectionEnd="<!-- html / body / javascript / end -->"

	with open('../.template-parts/html-body-js.template') as templateContentFile:

		contentToSubstituteIntoArea = templateContentFile.read()

	with open(fileToUpdate) as targetSubstitutionFile:
		s = targetSubstitutionFile.read()

	ddd=s

	ddd=re.sub(templateSectionBegin, templateSectionBegin+temporaryReplaceStringBegin, ddd, flags=re.DOTALL)

	ddd=re.sub(templateSectionEnd, temporaryReplaceStringEnd+templateSectionEnd, ddd, flags=re.DOTALL)
	ddd=re.sub(temporaryReplaceStringBegin+".*"+temporaryReplaceStringEnd,contentToSubstituteIntoArea,ddd,flags=re.DOTALL)

	f = open(fileToUpdate, "w")
	
	f.write(ddd)
	
	f.close()


def replaceTopNavigation():

	templateSectionBegin="<!-- html / navigation / top / begin -->"

	templateSectionEnd="<!-- html / navigation / top / end -->"

	with open('../.template-parts/html-top-nav.template') as templateContentFile:

		contentToSubstituteIntoArea = templateContentFile.read()

	with open(fileToUpdate) as targetSubstitutionFile:
		s = targetSubstitutionFile.read()

	ddd=s

	ddd=re.sub(templateSectionBegin, templateSectionBegin+temporaryReplaceStringBegin, ddd, flags=re.DOTALL)

	ddd=re.sub(templateSectionEnd, temporaryReplaceStringEnd+templateSectionEnd, ddd, flags=re.DOTALL)
	ddd=re.sub(temporaryReplaceStringBegin+".*"+temporaryReplaceStringEnd,contentToSubstituteIntoArea,ddd,flags=re.DOTALL)

	f = open(fileToUpdate, "w")
	
	f.write(ddd)
	
	f.close()

###################################################################
##
## driver
##
###################################################################


replaceCSSIncludes()

replaceJavascriptIncludes()

replaceTopNavigation()
