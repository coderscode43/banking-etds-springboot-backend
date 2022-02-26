"use strict";
App.controller("VendorController",["$http","restUrl","$scope","$state","$stateParams","CommonService","$q","$window",function(f,e,h,a,g,d,c,b){var i=this;
i.branchId;
i.vendorId;
i.entity={};
i.blg={};
i.vendor={};
i.vendorSectionAmount={};
i.nop={};
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
i.getVendorDetail=function(k,j){console.log("Common Controller is working");
var l={};
l.gstNo=k;
if(k!=null){d.search(g.clientId,j,l).then(function(m){console.log(name+" dynamic drop down");
i.vendor=m;
i.entity.vendorName=m.vendorName;
i.entity.vendorPAN=m.vendorPAN;
i.entity.vendorNo=m.vendorNo;
i.entity.vendorId=m.id
})
}};
i.getEntityData=function(){console.log("Common Controller get Entity data");
return d.getEntity()
};
i.getVendorDetail1=function(j,k){console.log("Common Controller is working");
var l={};
l.vendorNo=j;
if(j!=null){d.search(g.clientId,k,l).then(function(m){console.log(name+" dynamic drop down");
i.vendor=m;
i.entity.vendorName=m.vendorName;
i.entity.vendorPAN=m.vendorPAN;
i.entity.gstNo=m.gstNo;
i.entity.vendorId=m.id
})
}};
i.getVendorDetail2=function(k,j){console.log("Common Controller is working");
var l={};
l.vendorPAN=k;
if(k!=null){d.search(g.clientId,j,l).then(function(m){console.log(name+" dynamic drop down");
i.vendor=m;
i.entity.vendorName=m.vendorName;
i.entity.vendorNo=m.vendorNo;
i.entity.gstNo=m.gstNo;
i.entity.vendorId=m.id
})
}};
i.getVendorDetail3=function(l,j){console.log("Common Controller is working");
var k={};
k.vendorName=l;
if(l!=null){d.search(g.clientId,j,k).then(function(m){console.log(name+" dynamic drop down");
i.vendor=m;
i.entity.vendorPAN=m.vendorPAN;
i.entity.vendorNo=m.vendorNo;
i.entity.gstNo=m.gstNo;
i.entity.vendorId=m.id
})
}};
i.getBLGDetail=function(l,j){console.log("Common Controller is working");
var k={};
k.blgCode=l;
if(l!=null){d.search(g.clientId,j,k).then(function(m){console.log(name+" dynamic drop down");
i.blg=m;
i.blg.blgCode=m.blgCode
})
}};
i.getNOPDetail=function(j,k){console.log("Common Controller is working");
var l={};
l.natureOfPayment=j;
if(j!=null){d.search(g.clientId,k,l).then(function(m){console.log(name+" dynamic drop down");
i.nop=m;
i.nop.natureOfPayment=m.natureOfPayment;
i.nop.threshold=m.threshold
})
}};
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
i.entity.branchId=g.branchId;
i.entity.clientId=g.clientId;
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
i.updateData=function(l,k,m,j){if(l==true){console.log("Common Controller updateData "+k);
i.entity.clientId=g.clientId;
d.update(m,k,g.clientId).then(function(n){console.log(k+" updated successfully");
angular.element("#"+j).trigger("click");
$("#successMsg").find(".modal-header").find(".headingMsg").append("Successfull");
$("#successMsg").find(".modal-body").find(".msg").append(" Updated Successfully");
$("#successMsg").modal()
},function(n){console.error("Error while updating Details, "+status);
if(n.exceptionMsg!=null&&n.exceptionMsg!=undefined){$("#errorMsg").find(".modal-body").find(".msg").append("Can not Update "+n.entityName+" : "+n.exceptionMsg);
$("#errorMsg").modal()
}else{for(var o=0;
o<n.fieldErrors.length;
o++){var p=n.fieldErrors[o];
document.getElementById(p.fieldName).innerHTML=p.message
}}})
}};
i.autoCalTDS=function(j){var k="";
if(i.vendor.id==undefined){k=k+"Vendor"
}if(i.blg.blgCode==undefined){k=k+" , BLG Code"
}if(i.nop.natureOfPayment==undefined){k=k+" , Nature Of payment"
}if(k===""){}else{alert("Please select "+k)
}var l={};
d.search(g.clientId,"vendorSectionAmount",l).then(function(p){console.log(name+" dynamic drop down");
i.vendorSectionAmount=p;
i.vendorSectionAmount.vendorId=p.vendorId;
i.vendorSectionAmount.blgId=p.blgId;
i.vendorSectionAmount.nopId=p.nopId;
var q=i.entity.cgst;
var o=i.entity.sgst;
var u=i.entity.igst;
var m=i.entity.cess;
var n=i.entity.surcharge;
i.entity.totalInvoiceValue=+q.valueOf()+ +o.valueOf()+ +u.valueOf()+ +m.valueOf()+ +n.valueOf();
var t=0;
var r=i.vendorSectionAmount.totalAmt;
var s=i.entity.taxableValue;
t=+r.valueOf()+ +s.valueOf();
if(t>i.nop.threshold){i.entity.incomeTaxTds=(t*i.nop.rate)/100
}else{i.entity.incomeTaxTds=0
}if(s>i.nop.gstTDSThreshold){i.entity.gstTds=(s*i.nop.gstTDSRate)/100
}else{i.entity.gstTds=0
}i.entity.netAmountPaid=+(i.entity.gstTds).valueOf()+ +(i.entity.incomeTaxTds).valueOf()
})
}
}]);