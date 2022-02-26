"use strict";
App.controller("HomeController",["$scope","$state","LoginService",function(c,d,b){var a=this;
a.credentials;
a.successMessage="";
a.errorMessage="";
a.submitLogin=function(){a.loader=true;
console.log("Submitting");
b.authenticate(a.credentials).then(function(e){console.log("User successfully logged inn");
a.successMessage="User successfully logged Inn";
a.errorMessage="";
a.done=true;
a.credentials={};
d.go("home.homepage")
},function(e){console.error("Error while logging in");
a.errorMessage="Error while logging in : "+e.data.errorMessage;
a.successMessage=""
})
}
}]);