
# Table of Contents

1.  [How does it work](#orge83ff4d)
2.  [Important](#orgdcc2bd3)



<a id="orge83ff4d"></a>

# How does it work

Write two `.json` files as arguments after the program like so `java Example first.json second.json`
the following will start the program and generate a `.csv` file in which only the appropriate values
will be stored. 

Please note that you have to put the `.json` files in the project's directory to run properly.

To see the results for yourself delete the `.csv` file and run the program one more time. This
should generate a new `.csv` file containing the proper values.


<a id="orgdcc2bd3"></a>

# Important

Do not remove the `pom.xml` file from the project, it contains JSON.simple from Maven repository
as a dependency.

