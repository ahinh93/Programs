#!/bin/bash
# 
# You have to change 123456789 to your ISU student ID
#
echo
echo "Copy all java files under pwd and myUtil to Li's directory for grading"
echo
TARGET="/home/cli2/public/IT328/Students/123456789/"
mkdir $TARGET
cp * $TARGET
chmod go+wxr $TARGET
chmod go+wxr $TARGET/*
echo
echo