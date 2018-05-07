import numpy as np
import pandas as pd
import csv
from matplotlib import pyplot as plt
from sklearn.linear_model import LogisticRegression
from sklearn.model_selection import train_test_split
from sklearn.metrics import confusion_matrix
from sklearn.metrics import accuracy_score

weapons= ["S686", "S1897", "Sawed-Off", "S12K", "P92", "P1911", "P18C", "R45", "Tommy Gun", "Micro UZI", "UMP9", 
          "Vector", "VSS", "M249", "SCAR-L", "M416", "AKM", "M16A4", "Groza", "AUG", "Win94", "Kar98k", "SKS", 
          "Mini 14", "Mk14", "AWM", "M24", "Punch", "Pan", "Crossbow", "Machete", "Crowbar", "Sickle", "Grenade", 
          "Molotov"]

inputData = pd.read_csv("../data/smallMIRAMARdeaths.csv")

print(inputData.head())
print(inputData.describe())
print(inputData.corr())

features = inputData[["xPos", "yPos", "dist"]]
targetVariables = inputData.weapon

featureTrain, featureTest, targetTrain, targetTest = train_test_split(features,targetVariables, test_size=0.3)

model = LogisticRegression()
fittedModel = model.fit(featureTrain, targetTrain)
predictions = fittedModel.predict(featureTest)

print(confusion_matrix(targetTest, predictions))
print(accuracy_score(targetTest, predictions))
running = True
#while running:
#    xPos = int(input("What is your X Coordinate? "))
#    yPos = int(input("What is your Y Coordinate? "))
#    dist = int(input("How far are you away from your target (in meters)? "))
#    userinput = [[xPos, yPos, dist]]
#    print(userinput)
#    newPredict = model.predict(userinput)
#    print("X = %s | Predicted = %s --> %s" % (userinput[0], newPredict[0], weapons[newPredict[0]]))
#    test = input("run again? (y/n)")
#    if test == 'n':
#        running = False
print(">> Generating heatmap... 1m")
f = open('../data/output1.csv', 'w')
for y in range (0, 800000, 500):
    for x in range(0,800000, 500):
        userInput = [[800000-y, x, 1]]
        newPredict = model.predict(userInput)
        output = "%s%s" % (weapons[newPredict[0]], ",")
        if ((weapons[newPredict[0]] != "M416") and (weapons[newPredict[0]] != "Kar98k")):
            print("[%s,%s] 1m --> %s" % (x, y, weapons[newPredict[0]]))
        f.write(output)
    f.write('\n')
f.close()

print(">> Generating heatmap... 2m")
f = open('../data/output2.csv', 'w')
for y in range (0, 800000, 500):
    for x in range(0,800000, 500):
        userInput = [[800000-y, x, 2]]
        newPredict = model.predict(userInput)
        output = "%s%s" % (weapons[newPredict[0]], ",")
        if ((weapons[newPredict[0]] != "M416") and (weapons[newPredict[0]] != "Kar98k")):
            print("[%s,%s] 1m --> %s" % (x, y, weapons[newPredict[0]]))
        f.write(output)
    f.write('\n')
f.close()

print(">> Generating heatmap... 3m")
f = open('../data/output3.csv', 'w')
for y in range (0, 800000, 500):
    for x in range(0,800000, 500):
        userInput = [[800000-y, x, 3]]
        newPredict = model.predict(userInput)
        output = "%s%s" % (weapons[newPredict[0]], ",")
        if ((weapons[newPredict[0]] != "M416") and (weapons[newPredict[0]] != "Kar98k")):
            print("[%s,%s] 1m --> %s" % (x, y, weapons[newPredict[0]]))
        f.write(output)
    f.write('\n')
f.close()

print(">> Generating heatmap... 4m")
f = open('../data/output4.csv', 'w')
for y in range (0, 800000, 500):
    for x in range(0,800000, 500):
        userInput = [[800000-y, x, 4]]
        newPredict = model.predict(userInput)
        output = "%s%s" % (weapons[newPredict[0]], ",")
        if ((weapons[newPredict[0]] != "M416") and (weapons[newPredict[0]] != "Kar98k")):
            print("[%s,%s] 1m --> %s" % (x, y, weapons[newPredict[0]]))
        f.write(output)
    f.write('\n')
f.close()

print("Program Terminated")

