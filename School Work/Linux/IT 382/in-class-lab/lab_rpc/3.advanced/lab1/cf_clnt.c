/*
 * Please do not edit this file.
 * It was generated using rpcgen.
 */

#include <memory.h> /* for memset */
#include "cf.h"

/* Default timeout can be changed using clnt_control() */
static struct timeval TIMEOUT = { 25, 0 };

enum clnt_stat 
cfs_null_1(void *argp, void *clnt_res, CLIENT *clnt)
{
	return (clnt_call(clnt, CFS_NULL,
		(xdrproc_t) xdr_void, (caddr_t) argp,
		(xdrproc_t) xdr_void, (caddr_t) clnt_res,
		TIMEOUT));
}

enum clnt_stat 
cfs_read_1(cfs_pathname *argp, cfs_read_res *clnt_res, CLIENT *clnt)
{
	return (clnt_call(clnt, CFS_READ,
		(xdrproc_t) xdr_cfs_pathname, (caddr_t) argp,
		(xdrproc_t) xdr_cfs_read_res, (caddr_t) clnt_res,
		TIMEOUT));
}

enum clnt_stat 
cfs_write_1(cfs_write_arg *argp, cfs_error *clnt_res, CLIENT *clnt)
{
	return (clnt_call(clnt, CFS_WRITE,
		(xdrproc_t) xdr_cfs_write_arg, (caddr_t) argp,
		(xdrproc_t) xdr_cfs_error, (caddr_t) clnt_res,
		TIMEOUT));
}

enum clnt_stat 
cfs_mkdir_1(cfs_pathname *argp, cfs_error *clnt_res, CLIENT *clnt)
{
	return (clnt_call(clnt, CFS_MKDIR,
		(xdrproc_t) xdr_cfs_pathname, (caddr_t) argp,
		(xdrproc_t) xdr_cfs_error, (caddr_t) clnt_res,
		TIMEOUT));
}

enum clnt_stat 
cfs_mkfile_1(cfs_pathname *argp, cfs_error *clnt_res, CLIENT *clnt)
{
	return (clnt_call(clnt, CFS_MKFILE,
		(xdrproc_t) xdr_cfs_pathname, (caddr_t) argp,
		(xdrproc_t) xdr_cfs_error, (caddr_t) clnt_res,
		TIMEOUT));
}

enum clnt_stat 
cfs_delete_1(cfs_pathname *argp, cfs_error *clnt_res, CLIENT *clnt)
{
	return (clnt_call(clnt, CFS_DELETE,
		(xdrproc_t) xdr_cfs_pathname, (caddr_t) argp,
		(xdrproc_t) xdr_cfs_error, (caddr_t) clnt_res,
		TIMEOUT));
}

enum clnt_stat 
cfs_readdir_1(cfs_pathname *argp, cfs_readdir_res *clnt_res, CLIENT *clnt)
{
	return (clnt_call(clnt, CFS_READDIR,
		(xdrproc_t) xdr_cfs_pathname, (caddr_t) argp,
		(xdrproc_t) xdr_cfs_readdir_res, (caddr_t) clnt_res,
		TIMEOUT));
}
