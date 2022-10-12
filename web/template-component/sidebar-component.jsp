<aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
      <!-- Sidebar user panel -->
      <div class="user-panel">
	<div class="pull-left info">
	</div>
      </div>
      <!-- /.search form -->
      <!-- sidebar menu: : style can be found in sidebar.less -->
      <ul class="sidebar-menu">
	<li class="header">MAIN NAVIGATION</li>
        <li class="treeview" id="home">
            <a href="#">
            <i class="fa fa-home"></i> <span>Home</span>
          </a>
        </li>
        <li class="treeview" id="masterdata">
            <a href="#">
              <i class="fa fa-th-list"></i>
              <span>Masterdata</span>
              <i class="fa fa-angle-left pull-right"></i>
            </a>
            <ul class="treeview-menu">
                <li id="debituronly"><a href="<%=approot%>/menu/user.jsp"><i class="fa fa-circle-o"></i>User</a></li> 
            </ul>
            <ul class="treeview-menu">
                <li id="debituronly"><a href="<%=approot%>/menu/user.jsp"><i class="fa fa-circle-o"></i>Data Karyawan</a></li> 
            </ul>
            <ul class="treeview-menu">
                <li id="debituronly"><a href="<%=approot%>/menu/pendidikan.jsp"><i class="fa fa-circle-o"></i>Data Pendidikan</a></li> 
            </ul>
            <ul class="treeview-menu">
                <li id="debituronly"><a href="<%=approot%>/menu/mappendidikan.jsp"><i class="fa fa-circle-o"></i>Data Map Pendidikan</a></li> 
            </ul>
            <ul class="treeview-menu">
                <li id="debituronly"><a href="<%=approot%>/menu/mapsyaratpendidikanjabatan.jsp"><i class="fa fa-circle-o"></i>Data Map Syarat Pendidikan</a></li> 
            </ul>
            <ul class="treeview-menu">
                <li id="debituronly"><a href="<%=approot%>/menu/kompetensi.jsp"><i class="fa fa-circle-o"></i>Data Kompetensi</a></li> 
            </ul>
        </li>
      </ul>
    </section>
    <!-- /.sidebar -->
</aside>