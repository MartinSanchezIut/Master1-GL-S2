import os
import csv

# Pour extraire un certain nombre de TRUE / FALSE du fichier : claimskg_result.csv

# VARIABLES A MODIFIER
nombreDeTRUE = 750
nombreDeFALSE = 350
outputfileName = "output_" + str(nombreDeTRUE) + "-" + str(nombreDeFALSE) + ".csv"
# *-*-*-*-*

f = open("claimskg_result.csv", 'r+', encoding="utf8")
f_output = open(outputfileName , 'w+', newline='')

print(f.readline())
c_TRUE = 0
c_FALSE = 0

# sourceLenght = len(f.readlines())
# newSourceFile = list()

myWriter = csv.writer(f_output)
myReader = csv.reader(f)
for row in myReader:
    if (c_TRUE == nombreDeTRUE and c_FALSE == nombreDeFALSE):
        break
        
    if (row[4] == "TRUE" and c_TRUE < nombreDeTRUE): 
        c_TRUE += 1
        # print(str(row[4])  + " " + str(c_TRUE))
        myWriter.writerow(row)
        # row = ["", ""]
        continue

    if (row[4] == "FALSE" and c_FALSE < nombreDeFALSE): 
        c_FALSE += 1
        # print(str(row[4])  + " " + str(c_FALSE))
        myWriter.writerow(row)
        # row = ["", ""]
        continue

    #  newSourceFile.append(row)

# f.close()
# f = open("claimskg_result.csv" , 'w+', newline='')
# myWriter = csv.writer(f)
# for row in newSourceFile:
#     myWriter.writerow(row)
# print(newSourceFile)

f.close()
f_output.close()

print("True : " + str(c_TRUE) + "/" + str(nombreDeTRUE))
print("False : " + str(c_FALSE) + "/" + str(nombreDeFALSE))
# print(str(len(newSourceFile)) + " / " + str(sourceLenght))
print(outputfileName)
