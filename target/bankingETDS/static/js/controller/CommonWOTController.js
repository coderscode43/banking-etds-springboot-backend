"use strict";
App.controller("CommonWOTController",["$http","restUrl","$scope","$state","$stateParams","CommonService","$q","$window",function(f,e,h,a,g,d,c,b){var i=this;
i.search={};
i.entity={};
i.ajax=[];
i.ajax1=function(k,l,m){var n={};
n.name=l;
n.term=m;
var j=c.defer();
return d.ajax(k,n,g.clientId).then(function(p){console.log(l+" dynamic drop down");
var o=p;
i.ajax=p;
return p
})
};
i.gotoListPage=function(j,k){i.company=name;
i.show=false;
i.entity={};
i.search={};
i.entityList=[];
i.dropdown=[];
i.ajax=[];
i.temp={};
i.temp3={};
i.temp1=[];
i.company={};
i.currentPage=1;
i.batchColor={};
i.goodsColor=[];
i.b=[];
i.fileName=new FormData();
a.go("home.list",{entity:j,page:k})
};
i.gotoList2Page=function(j,k){a.go("home.listBranch",{entity:j,page:k})
};
i.submit=function(l,k,j){if(l==true){console.log("Common Controller submit "+k);
i.entity.branchId=g.clientId;
d.save(i.entity,k,g.clientId).then(function(m){console.log(k+" added successfully");
i.gotoList2Page(k,k);
angular.element("#"+j).trigger("click");
$("#successMsg").find(".modal-header").find(".headingMsg").append("Successfull!");
$("#successMsg").find(".modal-body").find(".msg").append(" Saved Successfully!");
$("#successMsg").modal()
},function(m){console.error("Error while creating saving Details, "+status);
if(m.exceptionMsg!=null&&m.exceptionMsg!=undefined){$("#errorMsg").find(".modal-body").find(".msg").append("Can not Save "+m.entityName+" : "+m.exceptionMsg);
$("#errorMsg").modal()
}else{for(var n=0;
n<m.fieldErrors.length;
n++){var o=m.fieldErrors[n];
document.getElementById(o.fieldName).innerHTML=o.message
}}})
}};
i.date=function(j){j=new Date(j.getTime()+Math.abs(j.getTimezoneOffset()*60000));
return j
};
i.searchEntities=function(k,j,l){if(k=true){$.each(i.search,function(m,n){if(n===""||n===null){delete i.search[m]
}});
i.search.clientId=g.clientId;
a.go("home.search",{entity:j,page:l,searchParams:JSON.stringify(i.search)})
}}
}]);