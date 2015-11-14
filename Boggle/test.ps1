echo "#####RUNNING#####"
Remove-Item *.class
Remove-Item *.java

#For-loop to itterate through test files
Get-ChildItem .\testfiles | ForEach-Object{

#Itterate and append output to "out" file
java BoggleSearch 4 -q -c | Out-File -FilePath .\out -Append
}

#Compare answer file line-by-line to generated output, then cleanup.
Compare-Object $(Get-Content out) $(Get-Content ans)
Remove-Item -Path out
echo "#####DONE#####"