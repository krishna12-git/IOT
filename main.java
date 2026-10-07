  444444444444444444444444444444444444444444444444444444444444444444444444444444444
   Routing and Navigation with $routeProvider 
1. Create a single-page application (SPA) with multiple views. Implement navigation 
using $routeProvider to navigate between these views. Each view should display 
different content. 
Focus: $routeProvider, ng-view, partial views.

<!DOCTYPE html>
<html ng-app="myApp">

<head>
    <meta charset="UTF-8">
    <title>AngularJS Routing Example</title>

    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.8.2/angular.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.8.2/angular-route.min.js"></script>
    <script src="app4.js"></script>

    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }

        nav a {
            margin-right: 15px;
            text-decoration: none;
            padding: 5px 10px;
            border-radius: 5px;
            background: #007BFF;
            color: white;
        }

        nav a.active {
            background: #0056b3;
        }
    </style>
</head>

<body>

    <h1>AngularJS SPA with Routing</h1>

    <!-- Navigation -->
    <nav>
        <a href="#!/home" ng-class="{'active': isActive('/')}">Home</a>
        <a href="#!/about" ng-class="{'active': isActive('/about')}">About</a>
        <a href="#!/contact" ng-class="{'active': isActive('/contact')}">Contact</a>
    </nav>

    <!-- View will be injected here -->
    <div ng-view></div>

</body>

</html>
home.html
<h2>Home</h2>
<p>{{message}}</p>
3. about.html
<h2>About</h2>
<p>{{message}}</p>
4. contact.html
<h2>Contact</h2>
<p>{{message}}</p>

app.js  
var app = angular.module("myApp", ["ngRoute"]);

app.config(function($routeProvider) {

    $routeProvider
        .when("/", {
            templateUrl: "home.html",
            controller: "HomeController"
        })
        .when("/about", {
            templateUrl: "about.html",
            controller: "AboutController"
        })
        .when("/contact", {
            templateUrl: "contact.html",
            controller: "ContactController"
        })
        .otherwise({
            redirectTo: "/"
        });
});

app.controller("HomeController", function($scope) {
    $scope.message = "Welcome to the Home Page!";
});

app.controller("AboutController", function($scope) {
    $scope.message = "Learn more about us on the about page.";
});

app.controller("ContactController", function($scope) {
    $scope.message = "Contact us at contact@example.com";
});

// To highlight active links
app.run(function($rootScope, $location) {

    $rootScope.isActive = function(viewLocation) {
        return viewLocation === $location.path();
    };

});

55555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555

Practical No:-05 
Service for Data Sharing and Logic Encapsulation 
1. Build an application with a service that manages shared data or performs complex 
business logic. Demonstrate how different controllers can interact with the service 
and update the shared data. 
Focus: service, data sharing, logic encapsulation.

index.jtml

<!DOCTYPE html>
<html ng-app="sharedApp">

<head>
    <meta charset="utf-8">
    <title>Data Sharing with Service</title>

    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.8.2/angular.min.js"></script>
</head>

<body>

    <div ng-controller="FirstController">
        <h2>First Controller</h2>
        <p>Message: {{ message }}</p>

        <input type="text" ng-model="newMessage">
        <button ng-click="updateMessage()">Update Message</button>
    </div>

    <hr>

    <div ng-controller="SecondController">
        <h2>Second Controller</h2>
        <p>Message from Service: {{ message }}</p>

        <button ng-click="clearMessage()">Clear Message</button>
    </div>

    <script src="app5.js"></script>

</body>

</html>

app.js
var app = angular.module("sharedApp", []);

// Service to manage shared data and business logic
app.service("DataService", function () {

    var sharedMessage = "Hello from the Service!";

    return {

        getMessage: function () {
            return sharedMessage;
        },

        setMessage: function (msg) {
            sharedMessage = msg;
        },

        clearMessage: function () {
            sharedMessage = "";
        }

    };

});

// First Controller
app.controller("FirstController", function ($scope, DataService) {

    $scope.message = DataService.getMessage();

    $scope.updateMessage = function () {

        DataService.setMessage($scope.newMessage);
        $scope.message = DataService.getMessage();

    };

});

// Second Controller
app.controller("SecondController", function ($scope, DataService) {

    $scope.message = DataService.getMessage();

    $scope.clearMessage = function () {

        DataService.clearMessage();
        $scope.message = DataService.getMessage();

    };

});

 6666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666
PRACTICAL NO. 06 
Implementing a Simple Search Functionality 
Q. Create an application that displays a list of items. Add a search input field 
that filters the list based on user input. Implement a case-insensitive search. 

index.html
<!DOCTYPE html>
<html ng-app="searchApp">

<head>
    <meta charset="utf-8">
    <title>Simple Search in AngularJS</title>

    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.8.2/angular.min.js"></script>
    <script src="app6.js"></script>
</head>

<body ng-controller="MainController">

    <h2>Item Search Example</h2>

    <!-- Search Input -->
    <input type="text" ng-model="searchText" placeholder="Search items...">

    <!-- Display Filtered List -->
    <ul>
        <li ng-repeat="item in items | filter:searchText">
            {{ item }}
        </li>
    </ul>

</body>

</html>

app.js
var app = angular.module("searchApp", []);

app.controller("MainController", function ($scope) {

    $scope.items = [
        "Apple",
        "Banana",
        "Orange",
        "Grapes",
        "Mango",
        "Pineapple",
        "Watermelon"
    ];

    // searchText is automatically updated via ng-model
    $scope.searchText = "";

});

 888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888
PRACTICAL NO: 08 
Creating a Reusable Modal Dialog 
Q. Develop a reusable modal dialog component using a custom directive or 
service. The modal should be customizable with title, content, and buttons. 
Demonstrate how to open and close the modal from different parts of the 
application.

index.html
<!DOCTYPE html>
<html lang="en" ng-app="myApp">

<head>
    <meta charset="UTF-8">
    <title>Reusable Modal Dialog in AngularJS</title>

    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.8.2/angular.min.js"></script>
    <script src="app8.js"></script>
</head>

<body ng-controller="MainCtrl">

    <h2>AngularJS Reusable Modal Example</h2>

    <!-- Button to open modal -->
    <button ng-click="openModal('Hello from MainCtrl', 'This is a modal opened from Main Controller!')">
        Open Modal (MainCtrl)
    </button>

    <!-- Another controller -->
    <div ng-controller="OtherCtrl">

        <button ng-click="openModal('OtherCtrl Modal', 'Content triggered from Other Controller!')">
            Open Modal (OtherCtrl)
        </button>

    </div>

    <!-- Our reusable modal directive -->
    <modal-dialog></modal-dialog>

</body>

</html>

app.js
var app = angular.module("myApp", []);

// Simple Modal Service
app.service("ModalService", function($rootScope) {

    this.show = (title, content, buttons) =>
        $rootScope.$broadcast("showModal", {
            title,
            content,
            buttons
        });

    this.hide = () =>
        $rootScope.$broadcast("hideModal");

});

// Reusable Modal Directive
app.directive("modalDialog", function(ModalService) {

    return {

        restrict: "E",

        template: `
            <div class="modal-backdrop" ng-show="isVisible">

                <div class="modal-box">

                    <div class="modal-header">
                        {{ data.title }}
                    </div>

                    <div class="modal-body">
                        {{ data.content }}
                    </div>

                    <div class="modal-footer">

                        <button ng-repeat="b in data.buttons"
                                ng-click="click(b)">
                            {{ b }}
                        </button>

                        <button ng-click="close()">
                            Close
                        </button>

                    </div>

                </div>

            </div>
        `,

        controller: function($scope) {

            $scope.isVisible = false;
            $scope.data = {};

            $scope.$on("showModal", (_, d) => {
                $scope.data = d;
                $scope.isVisible = true;
            });

            $scope.$on("hideModal", () =>
                $scope.isVisible = false
            );

            $scope.close = () =>
                ModalService.hide();

            $scope.click = b => {
                alert("Clicked: " + b);
                $scope.close();
            };

        }

    };

});

// Controllers using the same ModalService
app.controller("MainCtrl", ($scope, ModalService) =>
    $scope.openModal = (t, c) =>
        ModalService.show(t, c, ["OK", "Cancel"])
);

app.controller("OtherCtrl", ($scope, ModalService) =>
    $scope.openModal = (t, c) =>
        ModalService.show(t, c, ["Yes", "No"])
);
 9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999
PRACTICAL NO: 09 
Implementing a Drag-and-Drop Interface: 
Q. Build an application that allows users to drag and drop elements within a list 
or between lists.

index.html
<!DOCTYPE html>
<html ng-app="dragDropApp">

<head>
    <title>AngularJS Drag & Drop Example</title>

    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.8.2/angular.min.js"></script>

    <style>
        .list-container {
            display: inline-block;
            vertical-align: top;
            width: 200px;
            min-height: 200px;
            margin: 10px;
            padding: 10px;
            border: 2px dashed #aaa;
            border-radius: 8px;
        }

        .list-container h3 {
            text-align: center;
        }

        .draggable-item {
            padding: 8px;
            margin: 5px;
            background: #f4f4f4;
            border: 1px solid #ccc;
            border-radius: 5px;
            cursor: grab;
        }

        .drag-over {
            border-color: #00aaff;
            background: #e6f7ff;
        }

        .insert-marker {
            height: 5px;
            background: #00aaff;
            margin: 2px 0;
        }
    </style>
</head>

<body ng-controller="MainCtrl">

    <div class="list-container" droppable list="list1">

        <h3>List 1</h3>

        <div ng-repeat="item in list1 track by $index"
             draggable
             item="item"
             list="list1"
             index="$index"
             class="draggable-item">
            {{item}}
        </div>

    </div>

    <div class="list-container" droppable list="list2">

        <h3>List 2</h3>

        <div ng-repeat="item in list2 track by $index"
             draggable
             item="item"
             list="list2"
             index="$index"
             class="draggable-item">
            {{item}}
        </div>

    </div>

    <script src="app9.js"></script>

</body>

</html>

app.js
var app = angular.module("dragDropApp", []);

app.controller("MainCtrl", function($scope) {

    $scope.list1 = [
        "Apple",
        "Banana",
        "Mango"
    ];

    $scope.list2 = [
        "Carrot",
        "Potato",
        "Tomato"
    ];

});

// Draggable Directive
app.directive("draggable", function() {

    return {

        restrict: "A",

        scope: {
            item: "=item",
            list: "=list",
            index: "=index"
        },

        link: function(scope, element) {

            element.attr("draggable", true);

            element.on("dragstart", function(e) {

                let dragData = {
                    item: scope.item,
                    sourceListName:
                        scope.$parent.$parent.$id +
                        "_" +
                        (scope.list === scope.$parent.$parent.list1
                            ? "list1"
                            : "list2"),
                    sourceIndex: scope.index
                };

                e.dataTransfer.setData(
                    "text",
                    angular.toJson(dragData)
                );

                e.dataTransfer.effectAllowed = "move";

                element.addClass("dragging");

            });

            element.on("dragend", function() {
                element.removeClass("dragging");
            });

        }

    };

});

// Droppable Directive
app.directive("droppable", function() {

    return {

        restrict: "A",

        scope: {
            list: "=list"
        },

        link: function(scope, element) {

            element.on("dragover", function(e) {

                e.preventDefault();

                element.addClass("drag-over");

                e.dataTransfer.dropEffect = "move";

            });

            element.on("dragleave", function() {

                element.removeClass("drag-over");

            });

            element.on("drop", function(e) {

                e.preventDefault();

                element.removeClass("drag-over");

                var data = e.dataTransfer.getData("text");

                if (data) {

                    var parsed = angular.fromJson(data);

                    var item = parsed.item;
                    var sourceIndex = parsed.sourceIndex;

                    scope.$apply(function() {

                        // Remove from original list
                        if (scope.$parent.list1.indexOf(item) !== -1) {

                            var idx = scope.$parent.list1.indexOf(item);

                            scope.$parent.list1.splice(idx, 1);

                        } else if (scope.$parent.list2.indexOf(item) !== -1) {

                            var idx2 = scope.$parent.list2.indexOf(item);

                            scope.$parent.list2.splice(idx2, 1);

                        }

                        // Add to target list
                        if (scope.list.indexOf(item) === -1) {

                            scope.list.push(item);

                        }

                    });

                }

            });

        }

    };

});