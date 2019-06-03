<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">

	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta name="description" content="Xenon Boostrap Admin Panel" />
	<meta name="author" content="" />

	<title>Xenon - Advanced Plugins</title>

	<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Arimo:400,700,400italic">
	<link rel="stylesheet" href="assets/css/fonts/linecons/css/linecons.css">
	<link rel="stylesheet" href="assets/css/fonts/fontawesome/css/font-awesome.min.css">
	<link rel="stylesheet" href="assets/css/bootstrap.css">
	<link rel="stylesheet" href="assets/css/xenon-core.css">
	<link rel="stylesheet" href="assets/css/xenon-forms.css">
	<link rel="stylesheet" href="assets/css/xenon-components.css">
	<link rel="stylesheet" href="assets/css/xenon-skins.css">
	<link rel="stylesheet" href="assets/css/custom.css">

	<script src="assets/js/jquery-1.11.1.min.js"></script>

	<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
	<!--[if lt IE 9]>
		<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
		<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	<![endif]-->


</head>
<body class="page-body">

	<div class="settings-pane">
			
		<a href="#" data-toggle="settings-pane" data-animate="true">
			&times;
		</a>
		
		<div class="settings-pane-inner">
			
			<div class="row">
				
				<div class="col-md-4">
					
					<div class="user-info">
						
						<div class="user-image">
							<a href="extra-profile.html">
								<img src="assets/images/user-2.png" class="img-responsive img-circle" />
							</a>
						</div>
						
						<div class="user-details">
							
							<h3>
								<a href="extra-profile.html">John Smith</a>
								
								<!-- Available statuses: is-online, is-idle, is-busy and is-offline -->
								<span class="user-status is-online"></span>
							</h3>
							
							<p class="user-title">Web Developer</p>
							
							<div class="user-links">
								<a href="extra-profile.html" class="btn btn-primary">Edit Profile</a>
								<a href="extra-profile.html" class="btn btn-success">Upgrade</a>
							</div>
							
						</div>
						
					</div>
					
				</div>
				
				<div class="col-md-8 link-blocks-env">
					
					<div class="links-block left-sep">
						<h4>
							<span>Notifications</span>
						</h4>
						
						<ul class="list-unstyled">
							<li>
								<input type="checkbox" class="cbr cbr-primary" checked="checked" id="sp-chk1" />
								<label for="sp-chk1">Messages</label>
							</li>
							<li>
								<input type="checkbox" class="cbr cbr-primary" checked="checked" id="sp-chk2" />
								<label for="sp-chk2">Events</label>
							</li>
							<li>
								<input type="checkbox" class="cbr cbr-primary" checked="checked" id="sp-chk3" />
								<label for="sp-chk3">Updates</label>
							</li>
							<li>
								<input type="checkbox" class="cbr cbr-primary" checked="checked" id="sp-chk4" />
								<label for="sp-chk4">Server Uptime</label>
							</li>
						</ul>
					</div>
					
					<div class="links-block left-sep">
						<h4>
							<a href="#">
								<span>Help Desk</span>
							</a>
						</h4>
						
						<ul class="list-unstyled">
							<li>
								<a href="#">
									<i class="fa-angle-right"></i>
									Support Center
								</a>
							</li>
							<li>
								<a href="#">
									<i class="fa-angle-right"></i>
									Submit a Ticket
								</a>
							</li>
							<li>
								<a href="#">
									<i class="fa-angle-right"></i>
									Domains Protocol
								</a>
							</li>
							<li>
								<a href="#">
									<i class="fa-angle-right"></i>
									Terms of Service
								</a>
							</li>
						</ul>
					</div>
					
				</div>
				
			</div>
		
		</div>
		
	</div>
	
	<div class="page-container"><!-- add class "sidebar-collapsed" to close sidebar by default, "chat-visible" to make chat appear always -->
			
		<!-- Add "fixed" class to make the sidebar fixed always to the browser viewport. -->
		<!-- Adding class "toggle-others" will keep only one menu item open at a time. -->
		<!-- Adding class "collapsed" collapse sidebar root elements and show only icons. -->
		<div class="sidebar-menu toggle-others fixed">
		
			<div class="sidebar-menu-inner">
				
				<header class="logo-env">
		
					<!-- logo -->
					<div class="logo">
						<a href="dashboard-1.html" class="logo-expanded">
							<img src="assets/images/logo@2x.png" width="80" alt="" />
						</a>
		
						<a href="dashboard-1.html" class="logo-collapsed">
							<img src="assets/images/logo-collapsed@2x.png" width="40" alt="" />
						</a>
					</div>
		
					<!-- This will toggle the mobile menu and will be visible only on mobile devices -->
					<div class="mobile-menu-toggle visible-xs">
						<a href="#" data-toggle="user-info-menu">
							<i class="fa-bell-o"></i>
							<span class="badge badge-success">7</span>
						</a>
		
						<a href="#" data-toggle="mobile-menu">
							<i class="fa-bars"></i>
						</a>
					</div>
		
					<!-- This will open the popup with user profile settings, you can use for any purpose, just be creative -->
					<div class="settings-icon">
						<a href="#" data-toggle="settings-pane" data-animate="true">
							<i class="linecons-cog"></i>
						</a>
					</div>
		
					
				</header>
				
				
						
				<ul id="main-menu" class="main-menu">
					<!-- add class "multiple-expanded" to allow multiple submenus to open -->
					<!-- class "auto-inherit-active-class" will automatically add "active" class for parent elements who are marked already with class "active" -->
					<li>
						<a href="dashboard-1.html">
							<i class="linecons-cog"></i>
							<span class="title">Dashboard</span>
						</a>
						<ul>
							<li>
								<a href="dashboard-1.html">
									<span class="title">Dashboard 1</span>
								</a>
							</li>
							<li>
								<a href="dashboard-2.html">
									<span class="title">Dashboard 2</span>
								</a>
							</li>
							<li>
								<a href="dashboard-3.html">
									<span class="title">Dashboard 3</span>
								</a>
							</li>
							<li>
								<a href="dashboard-4.html">
									<span class="title">Dashboard 4</span>
								</a>
							</li>
							<li>
								<a href="skin-generator.html">
									<span class="title">Skin Generator</span>
								</a>
							</li>
							<li>
								<a href="update-highlights.html">
									<span class="title">Update Highlights</span>
									<span class="label label-pink pull-right hidden-collapsed">v1.3</span>
								</a>
							</li>
						</ul>
					</li>
					<li>
						<a href="layout-variants.html">
							<i class="linecons-desktop"></i>
							<span class="title">Layouts</span>
						</a>
						<ul>
							<li>
								<a href="layout-variants.html">
									<span class="title">Layout Variants &amp; API</span>
								</a>
							</li>
							<li>
								<a href="layout-collapsed-sidebar.html">
									<span class="title">Collapsed Sidebar</span>
								</a>
							</li>
							<li>
								<a href="layout-static-sidebar.html">
									<span class="title">Static Sidebar</span>
								</a>
							</li>
							<li>
								<a href="layout-horizontal-menu.html">
									<span class="title">Horizontal Menu</span>
								</a>
							</li>
							<li>
								<a href="layout-horizontal-plus-sidebar.html">
									<span class="title">Horizontal &amp; Sidebar Menu</span>
								</a>
							</li>
							<li>
								<a href="layout-horizontal-menu-click-to-open-subs.html">
									<span class="title">Horizontal Open On Click</span>
								</a>
							</li>
							<li>
								<a href="layout-horizontal-menu-min.html">
									<span class="title">Horizontal Menu Minimal</span>
								</a>
							</li>
							<li>
								<a href="layout-right-sidebar.html">
									<span class="title">Right Sidebar</span>
								</a>
							</li>
							<li>
								<a href="layout-chat-open.html">
									<span class="title">Chat Open</span>
								</a>
							</li>
							<li>
								<a href="layout-horizontal-sidebar-menu-collapsed-right.html">
									<span class="title">Both Menus &amp; Collapsed</span>
								</a>
							</li>
							<li>
								<a href="layout-boxed.html">
									<span class="title">Boxed Layout</span>
								</a>
							</li>
							<li>
								<a href="layout-boxed-horizontal-menu.html">
									<span class="title">Boxed &amp; Horizontal Menu</span>
								</a>
							</li>
						</ul>
					</li>
					<li>
						<a href="ui-panels.html">
							<i class="linecons-note"></i>
							<span class="title">UI Elements</span>
						</a>
						<ul>
							<li>
								<a href="ui-panels.html">
									<span class="title">Panels</span>
								</a>
							</li>
							<li>
								<a href="ui-buttons.html">
									<span class="title">Buttons</span>
								</a>
							</li>
							<li>
								<a href="ui-tabs-accordions.html">
									<span class="title">Tabs &amp; Accordions</span>
								</a>
							</li>
							<li>
								<a href="ui-modals.html">
									<span class="title">Modals</span>
								</a>
							</li>
							<li>
								<a href="ui-breadcrumbs.html">
									<span class="title">Breadcrumbs</span>
								</a>
							</li>
							<li>
								<a href="ui-blockquotes.html">
									<span class="title">Blockquotes</span>
								</a>
							</li>
							<li>
								<a href="ui-progressbars.html">
									<span class="title">Progress Bars</span>
								</a>
							</li>
							<li>
								<a href="ui-navbars.html">
									<span class="title">Navbars</span>
								</a>
							</li>
							<li>
								<a href="ui-alerts.html">
									<span class="title">Alerts</span>
								</a>
							</li>
							<li>
								<a href="ui-pagination.html">
									<span class="title">Pagination</span>
								</a>
							</li>
							<li>
								<a href="ui-typography.html">
									<span class="title">Typography</span>
								</a>
							</li>
							<li>
								<a href="ui-other-elements.html">
									<span class="title">Other Elements</span>
								</a>
							</li>
						</ul>
					</li>
					<li>
						<a href="ui-widgets.html">
							<i class="linecons-star"></i>
							<span class="title">Widgets</span>
						</a>
					</li>
					<li>
						<a href="mailbox-main.html">
							<i class="linecons-mail"></i>
							<span class="title">Mailbox</span>
							<span class="label label-success pull-right">5</span>
						</a>
						<ul>
							<li>
								<a href="mailbox-main.html">
									<span class="title">Inbox</span>
								</a>
							</li>
							<li>
								<a href="mailbox-compose.html">
									<span class="title">Compose Message</span>
								</a>
							</li>
							<li>
								<a href="mailbox-message.html">
									<span class="title">View Message</span>
								</a>
							</li>
						</ul>
					</li>
					<li>
						<a href="tables-basic.html">
							<i class="linecons-database"></i>
							<span class="title">Tables</span>
						</a>
						<ul>
							<li>
								<a href="tables-basic.html">
									<span class="title">Basic Tables</span>
								</a>
							</li>
							<li>
								<a href="tables-responsive.html">
									<span class="title">Responsive Table</span>
								</a>
							</li>
							<li>
								<a href="tables-datatables.html">
									<span class="title">Data Tables</span>
								</a>
							</li>
						</ul>
					</li>
					<li class="opened active">
						<a href="forms-native.html">
							<i class="linecons-params"></i>
							<span class="title">Forms</span>
						</a>
						<ul>
							<li>
								<a href="forms-native.html">
									<span class="title">Native Elements</span>
								</a>
							</li>
							<li class="active">
								<a href="forms-advanced.html">
									<span class="title">Advanced Plugins</span>
								</a>
							</li>
							<li>
								<a href="forms-wizard.html">
									<span class="title">Form Wizard</span>
								</a>
							</li>
							<li>
								<a href="forms-validation.html">
									<span class="title">Form Validation</span>
								</a>
							</li>
							<li>
								<a href="forms-input-masks.html">
									<span class="title">Input Masks</span>
								</a>
							</li>
							<li>
								<a href="forms-file-upload.html">
									<span class="title">File Upload</span>
								</a>
							</li>
							<li>
								<a href="forms-editors.html">
									<span class="title">Editors</span>
								</a>
							</li>
							<li>
								<a href="forms-sliders.html">
									<span class="title">Sliders</span>
								</a>
							</li>
						</ul>
					</li>
					<li>
						<a href="extra-gallery.html">
							<i class="linecons-beaker"></i>
							<span class="title">Extra</span>
							<span class="label label-purple pull-right hidden-collapsed">New Items</span>
						</a>
						<ul>
							<li>
								<a href="extra-icons-fontawesome.html">
									<span class="title">Icons</span>
								</a>
								<ul>
									<li>
										<a href="extra-icons-fontawesome.html">
											<span class="title">Font Awesome</span>
										</a>
									</li>
									<li>
										<a href="extra-icons-linecons.html">
											<span class="title">Linecons</span>
										</a>
									</li>
									<li>
										<a href="extra-icons-elusive.html">
											<span class="title">Elusive</span>
										</a>
									</li>
									<li>
										<a href="extra-icons-meteocons.html">
											<span class="title">Meteocons</span>
										</a>
									</li>
								</ul>
							</li>
							<li>
								<a href="extra-maps-google.html">
									<span class="title">Maps</span>
								</a>
								<ul>
									<li>
										<a href="extra-maps-google.html">
											<span class="title">Google Maps</span>
										</a>
									</li>
									<li>
										<a href="extra-maps-advanced.html">
											<span class="title">Advanced Map</span>
										</a>
									</li>
									<li>
										<a href="extra-maps-vector.html">
											<span class="title">Vector Map</span>
										</a>
									</li>
								</ul>
							</li>
							<li>
								<a href="extra-members-list.html">
									<span class="title">Members</span>
									<span class="label label-warning pull-right">New</span>
								</a>
								<ul>
									<li>
										<a href="extra-members-list.html">
											<span class="title">Members List</span>
										</a>
									</li>
									<li>
										<a href="extra-members-add.html">
											<span class="title">Add Member</span>
										</a>
									</li>
								</ul>
							</li>
							<li>
								<a href="extra-gallery.html">
									<span class="title">Gallery</span>
								</a>
							</li>
							<li>
								<a href="extra-calendar.html">
									<span class="title">Calendar</span>
								</a>
							</li>
							<li>
								<a href="extra-profile.html">
									<span class="title">Profile</span>
								</a>
							</li>
							<li>
								<a href="extra-login.html">
									<span class="title">Login</span>
								</a>
							</li>
							<li>
								<a href="extra-lockscreen.html">
									<span class="title">Lockscreen</span>
								</a>
							</li>
							<li>
								<a href="extra-login-light.html">
									<span class="title">Login Light</span>
								</a>
							</li>
							<li>
								<a href="extra-timeline.html">
									<span class="title">Timeline</span>
								</a>
							</li>
							<li>
								<a href="extra-timeline-center.html">
									<span class="title">Timeline Centerd</span>
								</a>
							</li>
							<li>
								<a href="extra-notes.html">
									<span class="title">Notes</span>
								</a>
							</li>
							<li>
								<a href="extra-image-crop.html">
									<span class="title">Image Crop</span>
								</a>
							</li>
							<li>
								<a href="extra-portlets.html">
									<span class="title">Portlets</span>
								</a>
							</li>
							<li>
								<a href="blank-sidebar.html">
									<span class="title">Blank Page</span>
								</a>
							</li>
							<li>
								<a href="extra-search.html">
									<span class="title">Search</span>
								</a>
							</li>
							<li>
								<a href="extra-invoice.html">
									<span class="title">Invoice</span>
								</a>
							</li>
							<li>
								<a href="extra-not-found.html">
									<span class="title">404 Page</span>
								</a>
							</li>
							<li>
								<a href="extra-tocify.html">
									<span class="title">Tocify</span>
								</a>
							</li>
							<li>
								<a href="extra-loader.html">
									<span class="title">Loading Progress</span>
								</a>
							</li>
							<li>
								<a href="extra-page-loading-ol.html">
									<span class="title">Page Loading Overlay</span>
								</a>
							</li>
							<li>
								<a href="extra-notifications.html">
									<span class="title">Notifications</span>
								</a>
							</li>
							<li>
								<a href="extra-nestable-lists.html">
									<span class="title">Nestable Lists</span>
								</a>
							</li>
							<li>
								<a href="extra-scrollable.html">
									<span class="title">Scrollable</span>
								</a>
							</li>
						</ul>
					</li>
					<li>
						<a href="charts-main.html">
							<i class="linecons-globe"></i>
							<span class="title">Charts</span>
						</a>
						<ul>
							<li>
								<a href="charts-main.html">
									<span class="title">Chart Variants</span>
								</a>
							</li>
							<li>
								<a href="charts-range.html">
									<span class="title">Range Selector</span>
								</a>
							</li>
							<li>
								<a href="charts-sparklines.html">
									<span class="title">Sparklines</span>
								</a>
							</li>
							<li>
								<a href="charts-map.html">
									<span class="title">Map Charts</span>
								</a>
							</li>
							<li>
								<a href="charts-gauges.html">
									<span class="title">Circular Gauges</span>
								</a>
							</li>
							<li>
								<a href="charts-bar-gauges.html">
									<span class="title">Bar Gauges</span>
								</a>
							</li>
						</ul>
					</li>
					<li>
						<a href="#">
							<i class="linecons-cloud"></i>
							<span class="title">Menu Levels</span>
						</a>
						<ul>
							<li>
								<a href="#">
									<i class="entypo-flow-line"></i>
									<span class="title">Menu Level 1.1</span>
								</a>
								<ul>
									<li>
										<a href="#">
											<i class="entypo-flow-parallel"></i>
											<span class="title">Menu Level 2.1</span>
										</a>
									</li>
									<li>
										<a href="#">
											<i class="entypo-flow-parallel"></i>
											<span class="title">Menu Level 2.2</span>
										</a>
										<ul>
											<li>
												<a href="#">
													<i class="entypo-flow-cascade"></i>
													<span class="title">Menu Level 3.1</span>
												</a>
											</li>
											<li>
												<a href="#">
													<i class="entypo-flow-cascade"></i>
													<span class="title">Menu Level 3.2</span>
												</a>
												<ul>
													<li>
														<a href="#">
															<i class="entypo-flow-branch"></i>
															<span class="title">Menu Level 4.1</span>
														</a>
													</li>
												</ul>
											</li>
										</ul>
									</li>
									<li>
										<a href="#">
											<i class="entypo-flow-parallel"></i>
											<span class="title">Menu Level 2.3</span>
										</a>
									</li>
								</ul>
							</li>
							<li>
								<a href="#">
									<i class="entypo-flow-line"></i>
									<span class="title">Menu Level 1.2</span>
								</a>
							</li>
							<li>
								<a href="#">
									<i class="entypo-flow-line"></i>
									<span class="title">Menu Level 1.3</span>
								</a>
							</li>
						</ul>
					</li>
				</ul>
				
			</div>
		
		</div>
	
		<div class="main-content">
					
			<nav class="navbar user-info-navbar"  role="navigation"><!-- User Info, Notifications and Menu Bar -->
			
				<!-- Left links for user info navbar -->
				<ul class="user-info-menu left-links list-inline list-unstyled">
			
					<li class="hidden-sm hidden-xs">
						<a href="#" data-toggle="sidebar">
							<i class="fa-bars"></i>
						</a>
					</li>
			
					<li class="dropdown hover-line">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">
							<i class="fa-envelope-o"></i>
							<span class="badge badge-green">15</span>
						</a>
			
						<ul class="dropdown-menu messages">
							<li>
								
								<ul class="dropdown-menu-list list-unstyled ps-scrollbar">
							
									<li class="active"><!-- "active" class means message is unread -->
										<a href="#">
											<span class="line">
												<strong>Luc Chartier</strong>
												<span class="light small">- yesterday</span>
											</span>
							
											<span class="line desc small">
												This ain’t our first item, it is the best of the rest.
											</span>
										</a>
									</li>
							
									<li class="active">
										<a href="#">
											<span class="line">
												<strong>Salma Nyberg</strong>
												<span class="light small">- 2 days ago</span>
											</span>
							
											<span class="line desc small">
												Oh he decisively impression attachment friendship so if everything.
											</span>
										</a>
									</li>
							
									<li>
										<a href="#">
											<span class="line">
												Hayden Cartwright
												<span class="light small">- a week ago</span>
											</span>
							
											<span class="line desc small">
												Whose her enjoy chief new young. Felicity if ye required likewise so doubtful.
											</span>
										</a>
									</li>
							
									<li>
										<a href="#">
											<span class="line">
												Sandra Eberhardt
												<span class="light small">- 16 days ago</span>
											</span>
							
											<span class="line desc small">
												On so attention necessary at by provision otherwise existence direction.
											</span>
										</a>
									</li>
							
									<!-- Repeated -->
							
									<li class="active"><!-- "active" class means message is unread -->
										<a href="#">
											<span class="line">
												<strong>Luc Chartier</strong>
												<span class="light small">- yesterday</span>
											</span>
							
											<span class="line desc small">
												This ain’t our first item, it is the best of the rest.
											</span>
										</a>
									</li>
							
									<li class="active">
										<a href="#">
											<span class="line">
												<strong>Salma Nyberg</strong>
												<span class="light small">- 2 days ago</span>
											</span>
							
											<span class="line desc small">
												Oh he decisively impression attachment friendship so if everything.
											</span>
										</a>
									</li>
							
									<li>
										<a href="#">
											<span class="line">
												Hayden Cartwright
												<span class="light small">- a week ago</span>
											</span>
							
											<span class="line desc small">
												Whose her enjoy chief new young. Felicity if ye required likewise so doubtful.
											</span>
										</a>
									</li>
							
									<li>
										<a href="#">
											<span class="line">
												Sandra Eberhardt
												<span class="light small">- 16 days ago</span>
											</span>
							
											<span class="line desc small">
												On so attention necessary at by provision otherwise existence direction.
											</span>
										</a>
									</li>
							
								</ul>
							
							</li>
							
							<li class="external">
								<a href="mailbox-main.html">
									<span>All Messages</span>
									<i class="fa-link-ext"></i>
								</a>
							</li>
						</ul>
					</li>
			
					<li class="dropdown hover-line">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">
							<i class="fa-bell-o"></i>
							<span class="badge badge-purple">7</span>
						</a>
			
						<ul class="dropdown-menu notifications">
							<li class="top">
								<p class="small">
									<a href="#" class="pull-right">Mark all Read</a>
									You have <strong>3</strong> new notifications.
								</p>
							</li>
							
							<li>
								<ul class="dropdown-menu-list list-unstyled ps-scrollbar">
									<li class="active notification-success">
										<a href="#">
											<i class="fa-user"></i>
											
											<span class="line">
												<strong>New user registered</strong>
											</span>
											
											<span class="line small time">
												30 seconds ago
											</span>
										</a>
									</li>
									
									<li class="active notification-secondary">
										<a href="#">
											<i class="fa-lock"></i>
											
											<span class="line">
												<strong>Privacy settings have been changed</strong>
											</span>
											
											<span class="line small time">
												3 hours ago
											</span>
										</a>
									</li>
									
									<li class="notification-primary">
										<a href="#">
											<i class="fa-thumbs-up"></i>
											
											<span class="line">
												<strong>Someone special liked this</strong>
											</span>
											
											<span class="line small time">
												2 minutes ago
											</span>
										</a>
									</li>
									
									<li class="notification-danger">
										<a href="#">
											<i class="fa-calendar"></i>
											
											<span class="line">
												John cancelled the event
											</span>
											
											<span class="line small time">
												9 hours ago
											</span>
										</a>
									</li>
									
									<li class="notification-info">
										<a href="#">
											<i class="fa-database"></i>
											
											<span class="line">
												The server is status is stable
											</span>
											
											<span class="line small time">
												yesterday at 10:30am
											</span>
										</a>
									</li>
									
									<li class="notification-warning">
										<a href="#">
											<i class="fa-envelope-o"></i>
											
											<span class="line">
												New comments waiting approval
											</span>
											
											<span class="line small time">
												last week
											</span>
										</a>
									</li>
								</ul>
							</li>
							
							<li class="external">
								<a href="#">
									<span>View all notifications</span>
									<i class="fa-link-ext"></i>
								</a>
							</li>
						</ul>
					</li>
			
					<!-- Added in v1.2 -->
					<li class="dropdown hover-line language-switcher">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">
							<img src="assets/images/flags/flag-uk.png" alt="flag-uk" />
							English
						</a>
			
						<ul class="dropdown-menu languages">
							<li>
								<a href="#">
									<img src="assets/images/flags/flag-al.png" alt="flag-al" />
									Shqip
								</a>
							</li>
							<li class="active">
								<a href="#">
									<img src="assets/images/flags/flag-uk.png" alt="flag-uk" />
									English
								</a>
							</li>
							<li>
								<a href="#">
									<img src="assets/images/flags/flag-de.png" alt="flag-de" />
									Deutsch
								</a>
							</li>
							<li>
								<a href="#">
									<img src="assets/images/flags/flag-fr.png" alt="flag-fr" />
									Fran&ccedil;ais
								</a>
							</li>
							<li>
								<a href="#">
									<img src="assets/images/flags/flag-br.png" alt="flag-br" />
									Portugu&ecirc;s
								</a>
							</li>
							<li>
								<a href="#">
									<img src="assets/images/flags/flag-es.png" alt="flag-es" />
									Espa&ntilde;ol
								</a>
							</li>
						</ul>
					</li>
			
				</ul>
			
			
				<!-- Right links for user info navbar -->
				<ul class="user-info-menu right-links list-inline list-unstyled">
							<li class="search-form"><!-- You can add "always-visible" to show make the search input visible -->
			
						<form name="userinfo_search_form" method="get" action="extra-search.html">
							<input type="text" name="s" class="form-control search-field" placeholder="Type to search..." />
			
							<button type="submit" class="btn btn-link">
								<i class="linecons-search"></i>
							</button>
						</form>
			
					</li>
			
					<li class="dropdown user-profile">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">
							<img src="assets/images/user-4.png" alt="user-image" class="img-circle img-inline userpic-32" width="28" />
							<span>
								John Smith
								<i class="fa-angle-down"></i>
							</span>
						</a>
			
						<ul class="dropdown-menu user-profile-menu list-unstyled">
							<li>
								<a href="#edit-profile">
									<i class="fa-edit"></i>
									New Post
								</a>
							</li>
							<li>
								<a href="#settings">
									<i class="fa-wrench"></i>
									Settings
								</a>
							</li>
							<li>
								<a href="#profile">
									<i class="fa-user"></i>
									Profile
								</a>
							</li>
							<li>
								<a href="#help">
									<i class="fa-info"></i>
									Help
								</a>
							</li>
							<li class="last">
								<a href="extra-lockscreen.html">
									<i class="fa-lock"></i>
									Logout
								</a>
							</li>
						</ul>
					</li>
			
					<li>
						<a href="#" data-toggle="chat">
							<i class="fa-comments-o"></i>
						</a>
					</li>
			
				</ul>
			
			</nav>
			
			<div class="page-title">
			
				<div class="title-env">
					<h1 class="title">Advanced Plugins</h1>
					<p class="description">Custom form elements using Bootstrap and jQuery plugins</p>
				</div>
			
					<div class="breadcrumb-env">
			
								<ol class="breadcrumb bc-1" >
									<li>
							<a href="dashboard-1.html"><i class="fa-home"></i>Home</a>
						</li>
								<li>
			
										<a href="forms-native.html">Forms</a>
								</li>
							<li class="active">
			
										<strong>Advanced Plugins</strong>
								</li>
								</ol>
						
				</div>
				
			</div>
			
			
			<div class="row">
				<div class="col-sm-6">
					
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">Select2 Elements</h3>
							<div class="panel-options">
								<a href="#" data-toggle="panel">
									<span class="collapse-icon">&ndash;</span>
									<span class="expand-icon">+</span>
								</a>
								<a href="#" data-toggle="remove">
									&times;
								</a>
							</div>
						</div>
						<div class="panel-body">
							
							<form role="form">
								
								<div class="form-group">
									<label class="control-label">Select list</label>
									
									<script type="text/javascript">
										jQuery(document).ready(function($)
										{
											$("#s2example-1").select2({
												placeholder: 'Select your country...',
												allowClear: true
											}).on('select2-open', function()
											{
												// Adding Custom Scrollbar
												$(this).data('select2').results.addClass('overflow-hidden').perfectScrollbar();
											});
											
										});
									</script>
									
									<select class="form-control" id="s2example-1">
										<option></option>
										<optgroup label="North America">
											<option>Alabama</option>
											<option>Alaska</option>
											<option>Arizona</option>
											<option>Arkansas</option>
											<option>California</option>
											<option>Colorado</option>
											<option>Connecticut</option>
											<option>Delaware</option>
											<option>Florida</option>
											<option>Georgia</option>
											<option>Hawaii</option>
											<option>Idaho</option>
											<option>Illinois</option>
											<option>Indiana</option>
											<option>Iowa</option>
											<option>Kansas</option>
											<option>Kentucky[C]</option>
											<option>Louisiana</option>
											<option>Maine</option>
											<option>Maryland</option>
											<option>Massachusetts[D]</option>
											<option>Michigan</option>
											<option>Minnesota</option>
											<option>Mississippi</option>
											<option>Missouri</option>
											<option>Montana</option>
											<option>Nebraska</option>
											<option>Nevada</option>
											<option>New Hampshire</option>
											<option>New Jersey</option>
											<option>New Mexico</option>
											<option>New York</option>
											<option>North Carolina</option>
											<option>North Dakota</option>
											<option>Ohio</option>
											<option>Oklahoma</option>
											<option>Oregon</option>
											<option>Pennsylvania[E]</option>
											<option>Rhode Island[F]</option>
											<option>South Carolina</option>
											<option>South Dakota</option>
											<option>Tennessee</option>
											<option>Texas</option>
											<option>Utah</option>
											<option>Vermont</option>
											<option>Virginia[G]</option>
											<option>Washington</option>
											<option>West Virginia</option>
											<option>Wisconsin</option>
											<option>Wyoming</option>
										</optgroup>
										<optgroup label="Europe">
											<option>Albania</option>
											<option>Andorra</option>
											<option>Armenia</option>
											<option>Austria</option>
											<option>Azerbaijan</option>
											<option>Belarus</option>
											<option>Belgium</option>
											<option>Bosnia & Herzegovina</option>
											<option>Bulgaria</option>
											<option>Croatia</option>
											<option>Cyprus</option>
											<option>Czech Republic</option>
											<option>Denmark</option>
											<option>Estonia</option>
											<option>Finland</option>
											<option>France</option>
											<option>Georgia</option>
											<option>Germany</option>
											<option>Greece</option>
											<option>Hungary</option>
											<option>Iceland</option>
											<option>Ireland</option>
											<option>Italy</option>
											<option>Kosovo</option>
											<option>Latvia</option>
											<option>Liechtenstein</option>
											<option>Lithuania</option>
											<option>Luxembourg</option>
											<option>Macedonia</option>
											<option>Malta</option>
											<option>Moldova</option>
											<option>Monaco</option>
											<option>Montenegro</option>
											<option>The Netherlands</option>
											<option>Norway</option>
											<option>Poland</option>
											<option>Portugal</option>
											<option>Romania</option>
											<option>Russia</option>
											<option>San Marino</option>
											<option>Serbia</option>
											<option>Slovakia</option>
											<option>Slovenia</option>
											<option>Spain</option>
											<option>Sweden</option>
											<option>Switzerland</option>
											<option>Turkey</option>
											<option>Ukraine</option>
											<option>United Kingdom</option>
											<option>Vatican City (Holy See)</option>
										</optgroup>
										<optgroup label="Asia">
											<option>Afghanistan</option>
											<option>Bahrain</option>
											<option>Bangladesh</option>
											<option>Bhutan</option>
											<option>Brunei</option>
											<option>Cambodia</option>
											<option>China</option>
											<option>East Timor</option>
											<option>India</option>
											<option>Indonesia</option>
											<option>Iran</option>
											<option>Iraq</option>
											<option>Israel</option>
											<option>Japan</option>
											<option>Jordan</option>
											<option>Kazakhstan</option>
											<option>Korea North</option>
											<option>Korea South</option>
											<option>Kuwait</option>
											<option>Kyrgyzstan</option>
											<option>Laos</option>
											<option>Lebanon</option>
											<option>Malaysia</option>
											<option>Maldives</option>
											<option>Mongolia</option>
											<option>Myanmar (Burma)</option>
											<option>Nepal</option>
											<option>Oman</option>
											<option>Pakistan</option>
											<option>The Philippines</option>
											<option>Qatar</option>
											<option>Russia</option>
											<option>Saudi Arabia</option>
											<option>Singapore</option>
											<option>Sri Lanka</option>
											<option>Syria</option>
											<option>Taiwan</option>
											<option>Tajikistan</option>
											<option>Thailand</option>
											<option>Turkey</option>
											<option>Turkmenistan</option>
											<option>United Arab Emirates</option>
											<option>Uzbekistan</option>
											<option>Vietnam</option>
											<option>Yemen</option>
										</optgroup>
									</select>
										
										
								</div>
								
								<div class="form-group">
									<label class="control-label">Multiple select</label>
									
										
									<script type="text/javascript">
										jQuery(document).ready(function($)
										{
											$("#s2example-2").select2({
												placeholder: 'Choose your favorite US Countries',
												allowClear: true
											}).on('select2-open', function()
											{
												// Adding Custom Scrollbar
												$(this).data('select2').results.addClass('overflow-hidden').perfectScrollbar();
											});
											
										});
									</script>
									
									<select class="form-control" id="s2example-2" multiple>
										<option></option>
										<optgroup label="United States">
											<option>Alabama</option>
											<option>Alaska</option>
											<option>Arizona</option>
											<option>Arkansas</option>
											<option selected>California</option>
											<option>Colorado</option>
											<option>Connecticut</option>
											<option>Delaware</option>
											<option selected>Florida</option>
											<option>Georgia</option>
											<option>Hawaii</option>
											<option>Idaho</option>
											<option>Illinois</option>
											<option>Indiana</option>
											<option>Iowa</option>
											<option>Kansas</option>
											<option>Kentucky[C]</option>
											<option>Louisiana</option>
											<option>Maine</option>
											<option>Maryland</option>
											<option>Massachusetts[D]</option>
											<option>Michigan</option>
											<option>Minnesota</option>
											<option>Mississippi</option>
											<option>Missouri</option>
											<option>Montana</option>
											<option>Nebraska</option>
											<option>Nevada</option>
											<option>New Hampshire</option>
											<option>New Jersey</option>
											<option>New Mexico</option>
											<option>New York</option>
											<option>North Carolina</option>
											<option>North Dakota</option>
											<option>Ohio</option>
											<option>Oklahoma</option>
											<option>Oregon</option>
											<option>Pennsylvania[E]</option>
											<option>Rhode Island[F]</option>
											<option>South Carolina</option>
											<option>South Dakota</option>
											<option>Tennessee</option>
											<option>Texas</option>
											<option>Utah</option>
											<option>Vermont</option>
											<option>Virginia[G]</option>
											<option selected>Washington</option>
											<option>West Virginia</option>
											<option>Wisconsin</option>
											<option>Wyoming</option>
										</optgroup>
									</select>
										
								</div>
								
								<div class="form-group">
									<label class="control-label">Templating</label>
										
									<script type="text/javascript">
										jQuery(document).ready(function($)
										{
											$("#s2example-3").select2({
												placeholder: 'Select country...',
												allowClear: true,
												minimumResultsForSearch: -1, // Hide the search bar
												formatResult: function(state)
												{
													return '<div style="background:url(http://www.geonames.org/flags/x/' + state.id + '.gif) no-repeat center center;background-size:100%;display:inline-block;position:relative;width:20px;height:15px;margin-right: 10px;top:2px;"></div>' 
															+ state.text;
												}
											}).on('select2-open', function()
											{
												// Adding Custom Scrollbar
												$(this).data('select2').results.addClass('overflow-hidden').perfectScrollbar();
											});
										});
									</script>
									
									<select class="form-control" id="s2example-3">
										<option></option>
										<option value="al">Albania</option>
										<option value="au">Australia</option>
										<option value="bd">Bandgladesh</option>
										<option value="br">Brasil</option>
										<option value="ca">Canada</option>
										<option value="cn">China</option>
										<option value="de">Germany</option>
										<option value="fr">France</option>
										<option value="gr">Greece</option>
										<option value="jp">Japan</option>
										<option value="xk">Kosovo</option>
										<option value="nl">Netherlands</option>
										<option value="no">Norway</option>
										<option value="ru">Russia</option>
										<option value="se">Sweden</option>
										<option value="tr">Turkey</option>
										<option value="uk">United Kingdom</option>
										<option value="us">United States</option>
									</select>
										
								</div>
								
								<div class="form-group">
									<label class="control-label">Remote Data</label> 
									<script type="text/javascript">
										jQuery(document).ready(function($)
										{
											$("#s2example-4").select2({
												minimumInputLength: 1,
												placeholder: 'Search',
												ajax: {
													url: "data/select2-remote-data.php",
													dataType: 'json',
													quietMillis: 100,
													data: function(term, page) {
														return {
															limit: -1,
															q: term
														};
													},
													results: function(data, page ) {
														return { results: data }
													}
												},
												formatResult: function(student) { 
													return "<div class='select2-user-result'>" + student.name + "</div>"; 
												},
												formatSelection: function(student) { 
													return  student.name; 
												}
												
											});
										});
									</script>
									
									<input type="hidden" name="s2example-4" id="s2example-4" />
										
								</div>
								
							</form>
						
						</div>
					</div>
				
				</div>
				
				<div class="col-sm-6">
					
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">SelectBoxIt</h3>
							<div class="panel-options">
									<a href="#" data-toggle="panel">
										<span class="collapse-icon">&ndash;</span>
										<span class="expand-icon">+</span>
									</a>
									<a href="#" data-toggle="remove">
										&times;
									</a>
							</div>
						</div>
						<div class="panel-body">
							
							<form role="form">
								
								<div class="form-group">
									<label class="control-label">Simple select</label>
									
									<script type="text/javascript">
										jQuery(document).ready(function($)
										{
											$("#sboxit-1").selectBoxIt().on('open', function()
											{
												// Adding Custom Scrollbar
												$(this).data('selectBoxSelectBoxIt').list.perfectScrollbar();
											});
										});
									</script>
									
									<select class="form-control" id="sboxit-1">
										<option value="al">Albania</option>
										<option value="au">Australia</option>
										<option value="bd">Bandgladesh</option>
										<option value="br">Brasil</option>
										<option value="ca">Canada</option>
										<option value="cn">China</option>
										<option value="de">Germany</option>
										<option value="fr">France</option>
										<option value="gr">Greece</option>
										<option value="jp">Japan</option>
										<option value="xk">Kosovo</option>
										<option value="nl">Netherlands</option>
										<option value="no">Norway</option>
										<option value="ru">Russia</option>
										<option value="se">Sweden</option>
										<option value="tr">Turkey</option>
										<option value="uk">United Kingdom</option>
										<option value="us">United States</option>
									</select>
										
								</div>
								
								<div class="form-group">
									<label class="control-label">Placeholder added</label>
									
									<script type="text/javascript">
										jQuery(document).ready(function($)
										{
											$("#sboxit-2").selectBoxIt({
												showFirstOption: false
											}).on('open', function()
											{
												// Adding Custom Scrollbar
												$(this).data('selectBoxSelectBoxIt').list.perfectScrollbar();
											});
										});
									</script>
									
									<select class="form-control" id="sboxit-2">
										<option>Select your country</option>
										<option value="al">Albania</option>
										<option value="au">Australia</option>
										<option value="bd">Bandgladesh</option>
										<option value="br">Brasil</option>
										<option value="ca">Canada</option>
										<option value="cn">China</option>
										<option value="de">Germany</option>
										<option value="fr">France</option>
										<option value="gr">Greece</option>
										<option value="jp">Japan</option>
										<option value="xk">Kosovo</option>
										<option value="nl">Netherlands</option>
										<option value="no">Norway</option>
										<option value="ru">Russia</option>
										<option value="se">Sweden</option>
										<option value="tr">Turkey</option>
										<option value="uk">United Kingdom</option>
										<option value="us">United States</option>
									</select>
										
								</div>
								
								<div class="form-group">
									<label class="control-label">Native dropdown menu</label>
									
									<script type="text/javascript">
										jQuery(document).ready(function($)
										{
											$("#sboxit-3").selectBoxIt({
												showFirstOption: false,
												"native": true
											});
										});
									</script>
									
									<select class="form-control" id="sboxit-3">
										<option>Select your country</option>
										<option value="al">Albania</option>
										<option value="au">Australia</option>
										<option value="bd">Bandgladesh</option>
										<option value="br">Brasil</option>
										<option value="ca">Canada</option>
										<option value="cn">China</option>
										<option value="de">Germany</option>
										<option value="fr">France</option>
										<option value="gr">Greece</option>
										<option value="jp">Japan</option>
										<option value="xk">Kosovo</option>
										<option value="nl">Netherlands</option>
										<option value="no">Norway</option>
										<option value="ru">Russia</option>
										<option value="se">Sweden</option>
										<option value="tr">Turkey</option>
										<option value="uk">United Kingdom</option>
										<option value="us">United States</option>
									</select>
										
								</div>
								
								<div class="form-group">
									<label class="control-label">Show and hide with effects</label>
									
									<script type="text/javascript">
										jQuery(document).ready(function($)
										{
											$("#sboxit-4").selectBoxIt({
												showEffect: 'fadeIn',
												hideEffect: 'fadeOut'
											});
										});
									</script>
									
									<select class="form-control" id="sboxit-4">
										<option>Select your country</option>
										<option value="al">Albania</option>
										<option value="au">Australia</option>
										<option value="bd">Bandgladesh</option>
										<option value="br">Brasil</option>
										<option value="ca">Canada</option>
										<option value="cn">China</option>
										<option value="de">Germany</option>
										<option value="fr">France</option>
										<option value="gr">Greece</option>
										<option value="jp">Japan</option>
										<option value="xk">Kosovo</option>
										<option value="nl">Netherlands</option>
										<option value="no">Norway</option>
										<option value="ru">Russia</option>
										<option value="se">Sweden</option>
										<option value="tr">Turkey</option>
										<option value="uk">United Kingdom</option>
										<option value="us">United States</option>
									</select>
										
								</div>
								
							</form>
						
						</div>
					</div>
				
				</div>
			</div>
			
			
			<div class="row">
				<div class="col-sm-6">
					
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">Tags Input</h3>
							<div class="panel-options">
								<a href="#" data-toggle="panel">
									<span class="collapse-icon">&ndash;</span>
									<span class="expand-icon">+</span>
								</a>
								<a href="#" data-toggle="remove">
									&times;
								</a>
							</div>
						</div>
						<div class="panel-body">
							
							<form role="form">
								
								<div class="form-group">
									<label class="control-label" for="tagsinput-1">Tags Input</label>
									<input type="text" class="form-control" id="tagsinput-1" data-role="tagsinput" value="Sample tag, Another great tag, Awesome!"  />	
								</div>
								
								<div class="form-group">
									<label class="control-label" for="tagsinput-1">Custom tag color</label>						
									
									<script type="text/javascript">
										jQuery(document).ready(function($)
										{
											var i = -1,
												colors = ['primary', 'secondary', 'red', 'blue', 'warning', 'success', 'purple'];
											
											colors = shuffleArray(colors);
											
											$("#tagsinput-2").tagsinput({
												tagClass: function()
												{
													i++;
													return "label label-" + colors[i%colors.length];
												}
											});
										});
										
										// Just for demo purpose
										function shuffleArray(array)
										{
											for (var i = array.length - 1; i > 0; i--) 
											{
												var j = Math.floor(Math.random() * (i + 1));
												var temp = array[i];
												array[i] = array[j];
												array[j] = temp;
											}
											return array;
										}
									</script>
									<input type="text" class="form-control" id="tagsinput-2" value="Sample tag, Another great tag, Awesome!, Every time, Different Color" />
								</div>
							
							</form>
						
						</div>
					</div>
				
				</div>
				<div class="col-sm-6">
					
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">Spinners</h3>
							<div class="panel-options">
								<a href="#" data-toggle="panel">
									<span class="collapse-icon">&ndash;</span>
									<span class="expand-icon">+</span>
								</a>
								<a href="#" data-toggle="remove">
									&times;
								</a>
							</div>
						</div>
						<div class="panel-body">
							
							<form role="form">
								
								<div class="form-group">
									<label class="control-label">Spinner Examples</label>
									
									<div class="row">
										<div class="col-md-6">
										
											<!-- Input spinner, just add class "spinner" to input-group and it will be activated -->
											<div class="input-group spinner" data-step="1">
												<span class="input-group-btn">
													<button class="btn btn-gray" data-type="decrement">-</button>
												</span>
												<input type="text" class="form-control text-center" value="1" />
												<span class="input-group-btn">
													<button class="btn btn-gray" data-type="increment">+</button>
												</span>
											</div>
											
										</div>
										<div class="col-md-6">
										
											<!-- Input spinner, just add class "spinner" to input-group and it will be activated -->
											<div class="input-group spinner" data-step="2" data-min="5" data-max="15">
												<span class="input-group-btn">
													<button class="btn btn-gray" data-type="decrement">-</button>
												</span>
												<input type="text" class="form-control text-center" readonly="1" value="7" />
												<span class="input-group-btn">
													<button class="btn btn-gray" data-type="increment">+</button>
												</span>
											</div>
											
										</div>
									</div>
									
									
									
									<br />
									<label class="control-label">Input Size</label>
									
									<!-- Input spinner, just add class "spinner" to input-group and it will be activated -->
									<div class="input-group input-group-lg spinner col-sm-12" data-step="5">
										<span class="input-group-btn">
											<button class="btn btn-info btn-single" data-type="decrement">-</button>
										</span>
										<input type="text" class="form-control text-center no-left-border" value="9" readonly />
										<span class="input-group-btn">
											<button class="btn btn-info btn-single" data-type="increment">+</button>
										</span>
									</div>
								</div>
							
							</form>
						
						</div>
					</div>
					
				</div>
			</div>
			
			
			<div class="row">
				<div class="col-sm-12">
					
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">Multi-select</h3>
							<div class="panel-options">
								<a href="#" data-toggle="panel">
									<span class="collapse-icon">&ndash;</span>
									<span class="expand-icon">+</span>
								</a>
								<a href="#" data-toggle="remove">
									&times;
								</a>
							</div>
						</div>
						<div class="panel-body">
							
							<form role="form" class="form-horizontal">
								
								<div class="form-group">
									<label class="col-sm-3 control-label" for="tagsinput-1">Multi-select List</label>
									
									<div class="col-sm-9">
										
										<script type="text/javascript">
											jQuery(document).ready(function($)
											{
												$("#multi-select").multiSelect({
													afterInit: function()
													{
														// Add alternative scrollbar to list
														this.$selectableContainer.add(this.$selectionContainer).find('.ms-list').perfectScrollbar();
													},
													afterSelect: function()
													{
														// Update scrollbar size
														this.$selectableContainer.add(this.$selectionContainer).find('.ms-list').perfectScrollbar('update');
													}
												});
											});
										</script>
										<select class="form-control" multiple="multiple" id="multi-select" name="my-select[]">
											<option value="1">Silky Door</option>
											<option value="2">The Absent Twilight</option>
											<option value="3">Tales of Flames</option>
											<option value="4">The Princess's Dream</option>
											<option value="5">The Fairy of the Wind</option>
											<option value="6">Children in the Boy</option>
											<option value="7">Frozen Savior</option>
											<option value="8">The Missing Thorns</option>
											<option value="9">Healing of Serpent</option>
											<option value="10">The Voyagers's Girlfriend</option>
											<option value="11">The Nothing of the Gate</option>
											<option value="12">Healing in the Scent</option>
											<option value="13">Final Twins</option>
											<option value="14">The Willing Rose</option>
											<option value="15">Thorn of Emperor</option>
											<option value="16" selected>The Predator's Pirates</option>
											<option value="17">The Lord of the Girl</option>
											<option value="18" selected>Flowers in the Spirit</option>
											<option value="19" selected>Healing in the Silence</option>
											<option value="20">Planet of Bridges</option>
										</select>
										
									</div>
								</div>
							
							</form>
						
						</div>
					</div>
				
				</div>
			</div>
			
			
			<div class="row">
				<div class="col-sm-12">
					
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">Typeahead Suggestions</h3>
							<div class="panel-options">
								<a href="#" data-toggle="panel">
									<span class="collapse-icon">&ndash;</span>
									<span class="expand-icon">+</span>
								</a>
								<a href="#" data-toggle="remove">
									&times;
								</a>
							</div>
						</div>
						<div class="panel-body">
							
							<form role="form" class="form-horizontal">
								
								<div class="form-group">
									<label class="col-sm-3 control-label" for="tagsinput-1">Auto-suggest (local dataset)</label>
									
									<div class="col-sm-9">
										
										<script type="text/javascript">
											jQuery(document).ready(function($)
											{
												var states = ['Alabama', 'Alaska', 'Arizona', 'Arkansas', 'California', 'Colorado', 'Connecticut', 'Delaware', 'Florida', 'Georgia', 'Hawaii', 'Idaho', 'Illinois', 'Indiana', 'Iowa', 'Kansas', 'Kentucky', 'Louisiana', 'Maine', 'Maryland', 'Massachusetts', 'Michigan', 'Minnesota', 'Mississippi', 'Missouri', 'Montana', 'Nebraska', 'Nevada', 'New Hampshire', 'New Jersey', 'New Mexico', 'New York', 'North Carolina', 'North Dakota', 'Ohio', 'Oklahoma', 'Oregon', 'Pennsylvania', 'Rhode Island', 'South Carolina', 'South Dakota', 'Tennessee', 'Texas', 'Utah', 'Vermont', 'Virginia', 'Washington', 'West Virginia', 'Wisconsin', 'Wyoming' ]
												
												var substringMatcher = function(strs) 
												{
													return function findMatches(q, cb) 
													{
														var matches, substrRegex;
														matches = [];
														substrRegex = new RegExp(q, 'i');
														
														$.each(strs, function(i, str) 
														{
															if (substrRegex.test(str)) 
															{
																matches.push({ value: str });
															}
														});
													 
														cb(matches);
													};
												};
												
												$("#typeahead-1").typeahead({
													hint: true,
													highlight: true,
													minLength: 1
												}, 
												{
													name: 'states', 
													displayKey: 'value', 
													source: substringMatcher(states)
												})
												.bind('typeahead:opened', function()
												{
													$(this).data('ttTypeahead').dropdown.$menu.addClass('overflow-hidden').perfectScrollbar();
												})
												.on('keyup', function()
												{
													$(this).data('ttTypeahead').dropdown.$menu.perfectScrollbar('update');
												});
												
											});
										</script>
										
										<div class="input-group">
											<input type="text" class="form-control" id="typeahead-1" />						
											<span class="input-group-addon">
												<i class="fa-globe"></i>
											</span>
										</div>
										
									</div>
								</div>
								
								<div class="form-group-separator"></div>
								
								<div class="form-group">
									<label class="col-sm-3 control-label" for="tagsinput-2">Pre-fetched dataset</label>
									
									<div class="col-sm-9">
									
										<script type="text/javascript">
											jQuery(document).ready(function($)
											{
												var names = new Bloodhound({
													datumTokenizer: Bloodhound.tokenizers.obj.whitespace('name'),
													queryTokenizer: Bloodhound.tokenizers.whitespace,
													limit: 10,
													prefetch: {
														url: 'data/names.json',
														filter: function(list) 
														{
															return $.map(list, function(name) { return { name: name }; });
														}
													}
												});
												
												names.initialize();
												
												$('#typeahead-2').typeahead(null, {
													name: 'names',
													displayKey: 'name',
													source: names.ttAdapter()
												});
											});
										</script>
										
										<div class="input-group">
											<span class="input-group-addon">
												<i class="fa-user"></i>
											</span>
											<input type="text" class="form-control" id="typeahead-2" placeholder="Enter your name" />
										</div>
										
									</div>
								</div>
								
								<div class="form-group-separator"></div>
								
								<div class="form-group">
									<label class="col-sm-3 control-label" for="tagsinput-2">Remote data suggestion</label>
									
									<div class="col-sm-9">
									
										<script type="text/javascript">
											jQuery(document).ready(function($)
											{	
												var name_randomizer = new Bloodhound({
													datumTokenizer: Bloodhound.tokenizers.obj.whitespace('value'),
													queryTokenizer: Bloodhound.tokenizers.whitespace,
													// You can also prefetch suggestions
													// prefetch: 'data/typeahead-generate.php',
													remote: 'data/typeahead-generate.php?q=%QUERY'
												});
												
												name_randomizer.initialize();
												
												$('#typeahead-3').typeahead({
													hint: true,
													highlight: true
												}, {
													name: 'string-randomizer',
													displayKey: 'value',
													source: name_randomizer.ttAdapter()
												});
											});
										</script>
										
										<input type="text" class="form-control" id="typeahead-3" placeholder="Type something" />
										
									</div>
								</div>
								
								<div class="form-group-separator"></div>
								
								<div class="form-group">
									<label class="col-sm-3 control-label" for="tagsinput-2">Templating suggestions</label>
									
									<div class="col-sm-9">
									
										<script type="text/javascript">
											jQuery(document).ready(function($)
											{	
												var harry_potter_movies = new Bloodhound({
													datumTokenizer: Bloodhound.tokenizers.obj.whitespace('name'),
													queryTokenizer: Bloodhound.tokenizers.whitespace,
													remote: 'data/typeahead-hp-movies.php?q=%QUERY'
												});
												
												harry_potter_movies.initialize();
												
												$('#typeahead-4').typeahead(null, {
													name: 'harry-potter-movies',
													displayKey: 'value',
													source: harry_potter_movies.ttAdapter(),
													templates: {
														empty: [
															'<div class="empty-message">',
															'We cannot find this movie title',
															'</div>'
														].join('\n'),
														suggestion: Handlebars.compile('<p class="clearfix"><img src="data/{{cover}}" class="img-responsive pull-left" width="30" style="margin-right:10px;" /><strong>{{value}}</strong> &mdash; {{year}}<br /><span style="display:inline-block; height: 22px; overflow: hidden; white-space:nowrap; text-overflow:ellipsis; max-width: 400px;">{{desc}}</span></p>')
													}
												})
												.bind('typeahead:opened', function()
												{
													$(this).data('ttTypeahead').dropdown.$menu.addClass('overflow-hidden').perfectScrollbar();
												})
												.on('keyup', function()
												{
													$(this).data('ttTypeahead').dropdown.$menu.perfectScrollbar('update');
												});
											});
										</script>
										
										<input type="text" class="form-control" id="typeahead-4" placeholder="Which Harry Potter movie do you like?" />
										
									</div>
								</div>
							
							</form>
						
						</div>
					</div>
				
				</div>
			</div>
			
			
			<div class="row">
				<div class="col-sm-12">
					
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">Date Picker</h3>
							<div class="panel-options">
								<a href="#" data-toggle="panel">
									<span class="collapse-icon">&ndash;</span>
									<span class="expand-icon">+</span>
								</a>
								<a href="#" data-toggle="remove">
									&times;
								</a>
							</div>
						</div>
						<div class="panel-body">
							
							<form role="form" class="form-horizontal">
								
								<div class="form-group">
									<label class="col-sm-3 control-label">Date Picker (inline)</label>
									
									<div class="col-sm-5">
										
										<div class="datepicker"></div><!-- add class "no-border" to remove the outline it -->
										
									</div>
								</div>
								
								<div class="form-group-separator"></div>
								
								<div class="form-group">
									<label class="col-sm-3 control-label">Date Picker (popup)</label>
									
									<div class="col-sm-9">
										<div class="input-group">
											<input type="text" class="form-control datepicker" data-format="D, dd MM yyyy">
											
											<div class="input-group-addon">
												<a href="#"><i class="linecons-calendar"></i></a>
											</div>
										</div>
									</div>
								</div>
								
								<div class="form-group-separator"></div>
								
								<div class="form-group">
									<label class="col-sm-3 control-label">Date Picker (start/end date)</label>
									
									<div class="col-sm-9">
										<input type="text" class="form-control datepicker" data-start-date="-2d" data-end-date="+1w">
									</div>
								</div>
								
								<div class="form-group-separator"></div>
								
								<div class="form-group">
									<label class="col-sm-3 control-label">Date Picker (disabled days Mo, We)</label>
									
									<div class="col-sm-9">
										<input type="text" class="form-control datepicker" data-disabled-days="1,3">
									</div>
								</div>
								
								<div class="form-group-separator"></div>
								
								<div class="form-group">
									<label class="col-sm-3 control-label">2 Clicks Date Select</label>
									
									<div class="col-sm-9">
										<input type="text" class="form-control datepicker" data-start-view="1">
									</div>
								</div>
								
								<div class="form-group-separator"></div>
								
								<div class="form-group">
									<label class="col-sm-3 control-label">3 Clicks Date Select</label>
									
									<div class="col-sm-9">
										<input type="text" class="form-control datepicker" data-start-view="2">
									</div>
								</div>
								
							</form>
						
						</div>
					</div>
				
				</div>
			</div>
			
			
			<div class="row">
				<div class="col-sm-12">
					
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">Time Picker</h3>
							<div class="panel-options">
								<a href="#" data-toggle="panel">
									<span class="collapse-icon">&ndash;</span>
									<span class="expand-icon">+</span>
								</a>
								<a href="#" data-toggle="remove">
									&times;
								</a>
							</div>
						</div>
						<div class="panel-body">
							
							<form role="form" class="form-horizontal">
								
								<div class="form-group">
									<label class="col-sm-3 control-label">Time Picker (Simple)</label>
									
									<div class="col-sm-2">
										<div class="input-group">
											<div class="input-group-addon">
												<i class="linecons-clock"></i>
											</div>
											<input type="text" class="form-control timepicker" />
										</div>
									</div>
								</div>
								
								<div class="form-group-separator"></div>
								
								<div class="form-group">
									<label class="col-sm-3 control-label">Time Picker (input-group)</label>
									
									<div class="col-sm-2">
										<div class="input-group input-group-minimal">
											<input type="text" class="form-control timepicker" data-template="dropdown" data-show-seconds="true" data-default-time="11:25 AM" data-show-meridian="true" data-minute-step="5" data-second-step="5" />
											
											<div class="input-group-addon">
												<a href="#"><i class="linecons-clock"></i></a>
											</div>
										</div>
									</div>
								</div>
								
								<div class="form-group-separator"></div>
								
								<div class="form-group">
									<label class="col-sm-3 control-label">Time Picker (Dropdown)</label>
									
									<div class="col-sm-3">
										<input type="text" class="form-control timepicker" data-template="dropdown" data-show-seconds="true" data-default-time="11:25 AM" data-show-meridian="true" data-minute-step="5" />
									</div>
								</div>
								
								<div class="form-group-separator"></div>
								
								<div class="form-group">
									<label class="col-sm-3 control-label">Date &amp; Time Picker</label>
									
									<div class="col-sm-4">
										
										<div class="date-and-time">
											<input type="text" class="form-control datepicker" data-format="D, dd MM yyyy">
											<input type="text" class="form-control timepicker" data-template="dropdown" data-show-seconds="true" data-default-time="11:25 AM" data-show-meridian="true" data-minute-step="5" data-second-step="5" />
										</div>
									</div>
								</div>
							</form>
							
						</div>
					</div>
				
				</div>
			</div>
			
			
			<div class="row">
				<div class="col-sm-12">
					
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">Date Range Picker</h3>
							<div class="panel-options">
								<a href="#" data-toggle="panel">
									<span class="collapse-icon">&ndash;</span>
									<span class="expand-icon">+</span>
								</a>
								<a href="#" data-toggle="remove">
									&times;
								</a>
							</div>
						</div>
						<div class="panel-body">
							
							<form role="form" class="form-horizontal">
								
								<div class="form-group">
									<label class="col-sm-3 control-label" for="field-1">Date Range Picker</label>
									
									<div class="col-sm-9">
										
										<input type="text" id="field-1" class="form-control daterange" />
										
									</div>
								</div>
								
								<div class="form-group-separator"></div>
								
								<div class="form-group">
									<label class="col-sm-3 control-label" for="field-2">Selected Date Range</label>
									
									<div class="col-sm-9">
										
										<input type="text" id="field-2" class="form-control daterange" data-format="YYYY-MM-DD" data-start-date="2013-08-02" data-end-date="2013-08-15" data-separator="," />
										
									</div>
								</div>
								
								<div class="form-group-separator"></div>
								
								<div class="form-group">
									<label class="col-sm-3 control-label" for="field-3">Disabled Date Range</label>
									
									<div class="col-sm-9">
										
										<input type="text" id="field-3" class="form-control daterange" data-min-date="12-01-2014" data-max-date="12-12-2014" />
										
									</div>
								</div>
								
								<div class="form-group-separator"></div>
								
								<div class="form-group">
									<label class="col-sm-3 control-label" for="field-4">Date Range Callback</label>
									
									<div class="col-sm-9">
										
										<input type="text" id="field-4" class="form-control daterange" data-callback />
										
									</div>
								</div>
								
								<div class="form-group-separator"></div>
								
								<div class="form-group">
									<label class="col-sm-3 control-label" for="field-5">Date Range with Timepicker</label>
									
									<div class="col-sm-9">
										
										<input type="text" id="field-5" class="form-control daterange" data-time-picker="true" data-time-picker-increment="5" data-format="MM/DD/YYYY h:mm A" />
										
									</div>
								</div>
								
								<div class="form-group-separator"></div>
								
								<div class="form-group">
									<label class="col-sm-3 control-label">Date Range Inline</label>
									
									<div class="col-sm-9">
										
																	<div class="daterange daterange-inline" data-format="MMMM D, YYYY" data-start-date="November 22, 2014" data-end-date="December 6, 2014">
											<i class="fa-calendar"></i>
											<span>November 22, 2014 - December 6, 2014</span>
										</div>
										
									</div>
								</div>
								
								<div class="form-group-separator"></div>
								
								<div class="form-group">
									<label class="col-sm-3 control-label">Date Range w/ Predefined Ranges</label>
									
									<div class="col-sm-9">
										
																	<div class="daterange daterange-inline add-ranges" data-format="MMMM D, YYYY" data-start-date="November 24, 2014" data-end-date="December 20, 2014">
											<i class="fa-calendar"></i>
											<span>November 24, 2014 - December 20, 2014</span>
										</div>
										
									</div>
								</div>
								
							</form>
						
						</div>
					</div>
				
				</div>
			</div>
			
			
			<div class="row">
				<div class="col-sm-12">
					
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">Color Picker</h3>
							<div class="panel-options">
								<a href="#" data-toggle="panel">
									<span class="collapse-icon">&ndash;</span>
									<span class="expand-icon">+</span>
								</a>
								<a href="#" data-toggle="remove">
									&times;
								</a>
							</div>
						</div>
						<div class="panel-body">
							
							<form role="form" class="form-horizontal">
								
								<div class="form-group">
									<label class="col-sm-3 control-label">Color Picker (Simple)</label>
									
									<div class="col-sm-9">
										<input type="text" class="form-control colorpicker" />
									</div>
								</div>
								
								<div class="form-group-separator"></div>
								
								<div class="form-group">
									<label class="col-sm-3 control-label">Color Picker (RGBA)</label>
									
									<div class="col-sm-9">
										<input type="text" class="form-control colorpicker" data-format="rgba" />
									</div>
								</div>
								
								<div class="form-group-separator"></div>
								
								<div class="form-group">
									<label class="col-sm-3 control-label">Color Picker (Horizontal)</label>
									
									<div class="col-sm-9">
										<input type="text" class="form-control colorpicker" data-horizontal="true" />
									</div>
								</div>
								
								<div class="form-group-separator"></div>
								
								<div class="form-group">
									<label class="col-sm-3 control-label">Color Picker Droplet</label>
									
									<div class="col-sm-2">
										<div class="input-group">
											<div class="input-group-addon">
												<a href="#"><i class="linecons-eye"></i></a>
											</div>
											
											<input type="text" class="form-control colorpicker" data-format="hex" />
										</div>
									</div>
								</div>
								
								<div class="form-group-separator"></div>
								
								<div class="form-group">
									<label class="col-sm-3 control-label">Color Picker Color Preview</label>
									
									<div class="col-sm-2">
										<div class="input-group">
											<input type="text" class="form-control colorpicker" data-format="hex" value="#5a3d3d" />
											
											<div class="input-group-addon">
												<i class="color-preview"></i>
											</div>
										</div>
									</div>
								</div>
							
							</form>
						
						</div>
					</div>
				
				</div>
			</div>
			
			
			<div class="row">
				<div class="col-sm-12">
					
					<a href="forms-icheck.html" class="btn btn-secondary">iCheck replacement is also integrated on Xenon, click here to see the examples <i class="fa-angle-right"></i></a>
					
				</div>
			</div>
			<!-- Main Footer -->
			<!-- Choose between footer styles: "footer-type-1" or "footer-type-2" -->
			<!-- Add class "sticky" to  always stick the footer to the end of page (if page contents is small) -->
			<!-- Or class "fixed" to  always fix the footer to the end of page -->
			<footer class="main-footer sticky footer-type-1">
				
				<div class="footer-inner">
				
					<!-- Add your copyright text here -->
					<div class="footer-text">
						&copy; 2014 
						<strong>Xenon</strong> 
						theme by <a href="http://laborator.co" target="_blank">Laborator</a> - <a href="http://themeforest.net/item/xenon-bootstrap-admin-theme/9059661?ref=Laborator" target="_blank">Purchase for only <strong>23$</strong></a>
					</div>
					
					
					<!-- Go to Top Link, just add rel="go-top" to any link to add this functionality -->
					<div class="go-up">
					
						<a href="#" rel="go-top">
							<i class="fa-angle-up"></i>
						</a>
						
					</div>
					
				</div>
				
			</footer>
		</div>
	
			
		<div id="chat" class="fixed"><!-- start: Chat Section -->
			
			<div class="chat-inner">
			
				
				<h2 class="chat-header">
					<a  href="#" class="chat-close" data-toggle="chat">
						<i class="fa-plus-circle rotate-45deg"></i>
					</a>
					
					Chat
					<span class="badge badge-success is-hidden">0</span>
				</h2>
				
				<script type="text/javascript">
				// Here is just a sample how to open chat conversation box
				jQuery(document).ready(function($)
				{
					var $chat_conversation = $(".chat-conversation");
					
					$(".chat-group a").on('click', function(ev)
					{
						ev.preventDefault();
						
						$chat_conversation.toggleClass('is-open');
						
						$(".chat-conversation textarea").trigger('autosize.resize').focus();
					});
					
					$(".conversation-close").on('click', function(ev)
					{
						ev.preventDefault();
						$chat_conversation.removeClass('is-open');
					});
				});
				</script>
				
				
				<div class="chat-group">
					<strong>Favorites</strong>
					
					<a href="#"><span class="user-status is-online"></span> <em>Catherine J. Watkins</em></a>
					<a href="#"><span class="user-status is-online"></span> <em>Nicholas R. Walker</em></a>
					<a href="#"><span class="user-status is-busy"></span> <em>Susan J. Best</em></a>
					<a href="#"><span class="user-status is-idle"></span> <em>Fernando G. Olson</em></a>
					<a href="#"><span class="user-status is-offline"></span> <em>Brandon S. Young</em></a>
				</div>
				
				
				<div class="chat-group">
					<strong>Work</strong>
					
					<a href="#"><span class="user-status is-busy"></span> <em>Rodrigo E. Lozano</em></a>
					<a href="#"><span class="user-status is-offline"></span> <em>Robert J. Garcia</em></a>
					<a href="#"><span class="user-status is-offline"></span> <em>Daniel A. Pena</em></a>
				</div>
				
				
				<div class="chat-group">
					<strong>Other</strong>
					
					<a href="#"><span class="user-status is-online"></span> <em>Dennis E. Johnson</em></a>
					<a href="#"><span class="user-status is-online"></span> <em>Stuart A. Shire</em></a>
					<a href="#"><span class="user-status is-online"></span> <em>Janet I. Matas</em></a>
					<a href="#"><span class="user-status is-online"></span> <em>Mindy A. Smith</em></a>
					<a href="#"><span class="user-status is-busy"></span> <em>Herman S. Foltz</em></a>
					<a href="#"><span class="user-status is-busy"></span> <em>Gregory E. Robie</em></a>
					<a href="#"><span class="user-status is-busy"></span> <em>Nellie T. Foreman</em></a>
					<a href="#"><span class="user-status is-busy"></span> <em>William R. Miller</em></a>
					<a href="#"><span class="user-status is-idle"></span> <em>Vivian J. Hall</em></a>
					<a href="#"><span class="user-status is-offline"></span> <em>Melinda A. Anderson</em></a>
					<a href="#"><span class="user-status is-offline"></span> <em>Gary M. Mooneyham</em></a>
					<a href="#"><span class="user-status is-offline"></span> <em>Robert C. Medina</em></a>
					<a href="#"><span class="user-status is-offline"></span> <em>Dylan C. Bernal</em></a>
					<a href="#"><span class="user-status is-offline"></span> <em>Marc P. Sanborn</em></a>
					<a href="#"><span class="user-status is-offline"></span> <em>Kenneth M. Rochester</em></a>
					<a href="#"><span class="user-status is-offline"></span> <em>Rachael D. Carpenter</em></a>
				</div>
			
			</div>
			
			<!-- conversation template -->
			<div class="chat-conversation">
				
				<div class="conversation-header">
					<a href="#" class="conversation-close">
						&times;
					</a>
					
					<span class="user-status is-online"></span>
					<span class="display-name">Arlind Nushi</span> 
					<small>Online</small>
				</div>
				
				<ul class="conversation-body">	
					<li>
						<span class="user">Arlind Nushi</span>
						<span class="time">09:00</span>
						<p>Are you here?</p>
					</li>
					<li class="odd">
						<span class="user">Brandon S. Young</span>
						<span class="time">09:25</span>
						<p>This message is pre-queued.</p>
					</li>
					<li>
						<span class="user">Brandon S. Young</span>
						<span class="time">09:26</span>
						<p>Whohoo!</p>
					</li>
					<li class="odd">
						<span class="user">Arlind Nushi</span>
						<span class="time">09:27</span>
						<p>Do you like it?</p>
					</li>
				</ul>
				
				<div class="chat-textarea">
					<textarea class="form-control autogrow" placeholder="Type your message"></textarea>
				</div>
				
			</div>
			
		<!-- end: Chat Section -->
		</div>
	
	</div>
	
	<div class="footer-sticked-chat"><!-- Start: Footer Sticked Chat -->
	
			<script type="text/javascript">
		function toggleSampleChatWindow()
		{
			var $chat_win = jQuery("#sample-chat-window");
	
			$chat_win.toggleClass('open');
	
			if($chat_win.hasClass('open'))
			{
				var $messages = $chat_win.find('.ps-scrollbar');
	
				if($.isFunction($.fn.perfectScrollbar))
				{
					$messages.perfectScrollbar('destroy');
	
					setTimeout(function(){
						$messages.perfectScrollbar();
						$chat_win.find('.form-control').focus();
					}, 300);
				}
			}
	
			jQuery("#sample-chat-window form").on('submit', function(ev)
			{
				ev.preventDefault();
			});
		}
	
		jQuery(document).ready(function($)
		{
			$(".footer-sticked-chat .chat-user, .other-conversations-list a").on('click', function(ev)
			{
				ev.preventDefault();
				toggleSampleChatWindow();
			});
	
			$(".mobile-chat-toggle").on('click', function(ev)
			{
				ev.preventDefault();
	
				$(".footer-sticked-chat").toggleClass('mobile-is-visible');
			});
		});
		</script>
		
		<ul class="chat-conversations list-unstyled">
	
			<!-- Extra Chat Conversations collected not to exceed window width -->
			<li class="browse-more">
				<a href="#" class="chat-user">
					<i class="linecons-comment"></i>
					<span>3</span>
				</a>
	
				<!-- These conversations are hidden in screen -->
				<ul class="other-conversations-list">
					<li>
						<!-- Minimal User Info Link -->
						<a href="#" >
							Catherine J. Watkins
							<span>&times;</span>
						</a>
					</li>
					<li>
						<!-- Minimal User Info Link -->
						<a href="#" >
							Nicholas R. Walker
							<span>&times;</span>
						</a>
					</li>
					<li>
						<!-- Minimal User Info Link -->
						<a href="#" >
							Susan J. Best
							<span>&times;</span>
						</a>
					</li>
				</ul>
			</li>
	
			<li id="sample-chat-window">
				<!-- User Info Link -->
				<a href="#"  class="chat-user">
					<span class="user-status is-online"></span>
					Art Ramadani
				</a>
	
				<span class="badge badge-purple">4</span>
	
				<!-- Conversation Window -->
				<div class="conversation-window">
					<!-- User Info Link in header (used to close the chat bar) -->
					<a href="#"  class="chat-user">
						<span class="close">&times;</span>
	
						<span class="user-status is-online"></span>
						Art Ramadani
					</a>
	
					<ul class="conversation-messages ps-scrollbar ps-scroll-down">
	
						<!-- Will indicate time -->
						<li class="time">Thursday 04, December '14</li>
	
						<li>
							<div class="user-info">
								<a href="#">
									<img src="assets/images/user-1.png" width="30" height="30" alt="user-image" />
								</a>
							</div>
							<div class="message-entry">
								<p>Hello John, how are you?</p>
							</div>
						</li>
	
						<li class="me"><!-- adding class="me" will indicate that "you" are sending a message -->
							<div class="message-entry">
								<p>Hi Art, I am fine :) How about you?</p>
							</div>
							<div class="user-info">
								<a href="#">
									<img src="assets/images/user-4.png" width="30" height="30" alt="user-image" />
								</a>
							</div>
						</li>
	
						<li>
							<div class="user-info">
								<a href="#">
									<img src="assets/images/user-1.png" width="30" height="30" alt="user-image" />
								</a>
							</div>
							<div class="message-entry">
								<p>Warmth his law design say are person. Pronounce suspected in belonging conveying ye repulsive.</p>
							</div>
						</li>
	
						<li class="me"><!-- adding class="me" will indicate that "you" are sending a message -->
							<div class="message-entry">
								<p>Comfort reached gay perhaps chamber his six detract besides add. Moonlight newspaper.</p>
								<p>Timed voice share led his widen noisy young.</p>
								<p>His six detract besides add moonlight newspaper.</p>
							</div>
							<div class="user-info">
								<a href="#">
									<img src="assets/images/user-4.png" width="30" height="30" alt="user-image" />
								</a>
							</div>
						</li>
	
						<li>
							<div class="user-info">
								<a href="#">
									<img src="assets/images/user-1.png" width="30" height="30" alt="user-image" />
								</a>
							</div>
							<div class="message-entry">
								<p>Hello John, how are you?</p>
							</div>
						</li>
	
						<li class="me"><!-- adding class="me" will indicate that "you" are sending a message -->
							<div class="message-entry">
								<p>Hi Art, I am fine :) How about you?</p>
							</div>
							<div class="user-info">
								<a href="#">
									<img src="assets/images/user-4.png" width="30" height="30" alt="user-image" />
								</a>
							</div>
						</li>
	
						<li>
							<div class="user-info">
								<a href="#">
									<img src="assets/images/user-1.png" width="30" height="30" alt="user-image" />
								</a>
							</div>
							<div class="message-entry">
								<p>Hello John, how are you?</p>
							</div>
						</li>
	
						<!-- Will indicate time -->
						<li class="time">Today 17:12</li>
	
						<li class="me"><!-- adding class="me" will indicate that "you" are sending a message -->
							<div class="message-entry">
								<p>Hi Art, I am fine :) How about you?</p>
	
														</div>
							<div class="user-info">
								<a href="#">
									<img src="assets/images/user-4.png" width="30" height="30" alt="user-image" />
								</a>
							</div>
						</li>
	
					</ul>
	
					<form method="post" class="chat-form">
						<input type="text" class="form-control"  placeholder="Enter your message..." />
					</form>
				</div>
			</li>
	
			<li>
				<!-- User Info Link -->
				<a href="#"  class="chat-user">
					<span class="user-status is-idle"></span>
					Ylli Pylla
				</a>
			</li>
	
			<li>
				<!-- User Info Link -->
				<a href="#"  class="chat-user">
					<span class="user-status is-busy"></span>
					Arlind Nushi
				</a>
			</li>
	
		</ul>
	
		<a href="#" class="mobile-chat-toggle">
			<i class="linecons-comment"></i>
			<span class="num">6</span>
			<span class="badge badge-purple">4</span>
		</a>
	
	<!-- End: Footer Sticked Chat -->
	</div>
	
	




	<!-- Imported styles on this page -->
	<link rel="stylesheet" href="assets/js/daterangepicker/daterangepicker-bs3.css">
	<link rel="stylesheet" href="assets/js/select2/select2.css">
	<link rel="stylesheet" href="assets/js/select2/select2-bootstrap.css">



	<script src="assets/js/moment.min.js"></script>


	<!-- Imported scripts on this page -->
	<script src="assets/js/daterangepicker/daterangepicker.js"></script>
	<script src="assets/js/datepicker/bootstrap-datepicker.js"></script>
	<script src="assets/js/timepicker/bootstrap-timepicker.min.js"></script>
	<script src="assets/js/colorpicker/bootstrap-colorpicker.min.js"></script>
	<script src="assets/js/select2/select2.min.js"></script>
	<script src="assets/js/jquery-ui/jquery-ui.min.js"></script>
	<script src="assets/js/selectboxit/jquery.selectBoxIt.min.js"></script>
	<script src="assets/js/tagsinput/bootstrap-tagsinput.min.js"></script>
	<script src="assets/js/typeahead.bundle.js"></script>
	<script src="assets/js/handlebars.min.js"></script>
	<script src="assets/js/multiselect/js/jquery.multi-select.js"></script>




</body>
</html>